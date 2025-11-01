package com.suleyman.stock_market_api.service;

import com.suleyman.stock_market_api.dto.CurrentStockDetailsDto;
import com.suleyman.stock_market_api.dto.StockDto;
import com.suleyman.stock_market_api.dto.StockWithPriceDto;
import com.suleyman.stock_market_api.entity.CurrentStockDetails;
import com.suleyman.stock_market_api.entity.Stock;
import com.suleyman.stock_market_api.repository.CurrentStockDetailsRepository;
import com.suleyman.stock_market_api.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepo;
    private final CurrentStockDetailsRepository stockDetailsRepo;
    private final WebClient webClient;
    @Value("${polygon.api-key}")
    private String apiKey;

    @Autowired
    public StockService(WebClient webClient, StockRepository stockRepo, CurrentStockDetailsRepository stockDetailsRepo) {
        this.webClient = webClient;
        this.stockRepo = stockRepo;
        this.stockDetailsRepo = stockDetailsRepo;
    }

    // External API related stocks

    /**
     * Method used by the controller to respond to the client with the requested stock and its price details
     * @param ticker ticker of the requested stock
     * @return DTO containing all the stock information
     */
    public StockWithPriceDto getStockWithLatestPrice(String ticker) {
        logger.info("Polygon API key configured as: {}", this.apiKey);
        String normalized = ticker.toUpperCase();

        // 1️⃣ Make sure stock exists (fetch from Polygon if missing)
        Stock stock = getOrFetchStock(normalized);

        // 2️⃣ Always fetch the latest price from Polygon and upsert into DB
        CurrentStockDetails latestDetails = fetchAndSaveLatestPrice(normalized, stock);

        // 3️⃣ Combine them into one DTO
        return new StockWithPriceDto(
                stock.getTicker(),
                stock.getName(),
                stock.getCurrency(),
                stock.getPrimaryExchange(),
                stock.getActive(),
                latestDetails.getDelayedCurrentPrice(),
                latestDetails.getPriceAtMarketOpen(),
                latestDetails.getHigh(),
                latestDetails.getLow(),
                latestDetails.getVwap(),
                latestDetails.getVolume(),
                latestDetails.getStockPriceTimestamp()
        );
    }

    /**
     * Helper method to fetch the stock or get the stock from DB if it already exists
     * @param ticker ticker of the stock
     * @return the stock with the requested ticker
     */
    private Stock getOrFetchStock(String ticker) {
        Optional<Stock> existing = this.getStock(ticker);
        if (existing.isPresent()) {
            return existing.get();
        }

        StockDto dto = fetchStockInfoFromPolygon(ticker);
        Stock entity = new Stock();
        entity.setTicker(dto.getTicker());
        entity.setName(dto.getName());
        entity.setCurrency(dto.getCurrency());
        entity.setPrimaryExchange(dto.getPrimaryExchange());
        entity.setActive(dto.getActive());

        Stock stock = stockRepo.save(entity);
        logger.info("Fetched the stock {} from external API and stored it in DB", ticker);

        return stock;
    }

    /**
     * This helper method fetches the current price details from the external API and updates the DB row
     * @param ticker ticker of the stock
     * @param stock stock object
     * @return current stock price details
     */
    private CurrentStockDetails fetchAndSaveLatestPrice(String ticker, Stock stock) {
        // Fetching price details of the stock with a given ticker from Polygon
        CurrentStockDetailsDto dto = fetchCurrentPriceDetailsFromPolygon(ticker);

        Optional<CurrentStockDetails> existingOpt = this.getCurrentStockDetails(ticker);

        CurrentStockDetails details;
        if (existingOpt.isPresent()) {
            details = existingOpt.get();
            details.setStockPriceTimestamp(dto.getStockPriceTimestamp());
            details.setVolume(dto.getVolume());
            details.setDelayedCurrentPrice(dto.getDelayedCurrentPrice());
            details.setPriceAtMarketOpen(dto.getPriceAtMarketOpen());
            details.setHigh(dto.getHigh());
            details.setLow(dto.getLow());
            details.setVwap(dto.getVwap());
            logger.info("Updated the currentStockDetails entity with ticker {}", ticker);
        }
        else {
            details = new CurrentStockDetails(
                    stock,
                    dto.getStockPriceTimestamp(),
                    dto.getVolume(),
                    dto.getDelayedCurrentPrice(),
                    dto.getPriceAtMarketOpen(),
                    dto.getHigh(),
                    dto.getLow(),
                    dto.getVwap()
            );
            logger.info("Updated the currentStockDetails entity with ticker {}", ticker);
        }

        return stockDetailsRepo.save(details);
    }

    // External API call methods

    /**
     * This method fetches the stock info from the external API
     * @param ticker ticker of the stock
     * @return fetched information in the dto form
     */
    private StockDto fetchStockInfoFromPolygon(String ticker) {
        Map<String, Object> response = webClient.get()
                .uri("/v3/reference/tickers/{ticker}?apiKey={apiKey}", ticker, apiKey)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> results = (Map<String, Object>) response.get("results");

        return new StockDto(
                (String) results.get("ticker"),
                (String) results.get("name"),
                (String) results.get("primary_exchange"),
                (String) results.get("currency_name"),
                (Boolean) results.get("active")
        );
    }

    /**
     * This method fetches the stock price details from the external API
     * @param ticker ticker of the stock
     * @return fetched information in the dto form
     */
    /**
     * Fetches the latest price details for the given stock ticker from Polygon.
     */
    private CurrentStockDetailsDto fetchCurrentPriceDetailsFromPolygon(String ticker) {
        Map<String, Object> response = webClient.get()
                .uri("/v2/aggs/ticker/{ticker}/prev?adjusted=true&apiKey={apiKey}", ticker, apiKey)
                .retrieve()
                .onStatus(status -> status.isError(), clientResponse ->
                        clientResponse.bodyToMono(String.class).map(body ->
                                new RuntimeException("Polygon error " + clientResponse.statusCode() + ": " + body)
                        )
                )
                .bodyToMono(Map.class)
                .block();

        // Extract "results" array from Polygon response
        java.util.List<Map<String, Object>> results =
                (java.util.List<Map<String, Object>>) response.get("results");

        Map<String, Object> r = results.get(0);

        // Parse safely — Polygon often sends scientific notation (e.g., 1.79798344E8)
        long volume = toLong(r.get("v"));
        long tsMillis = toLong(r.get("t"));
        Instant timestamp = Instant.ofEpochMilli(tsMillis);

        BigDecimal close = new BigDecimal(r.get("c").toString());
        BigDecimal open = new BigDecimal(r.get("o").toString());
        BigDecimal high = new BigDecimal(r.get("h").toString());
        BigDecimal low = new BigDecimal(r.get("l").toString());
        BigDecimal vwap = new BigDecimal(r.get("vw").toString());

        // Follow your DTO constructor order exactly
        return new CurrentStockDetailsDto(
                ticker,
                timestamp,
                volume,
                close,
                open,
                high,
                low,
                vwap
        );
    }

    /**
     * Converts Polygon numeric responses (Integer, Double, BigDecimal, or String in scientific notation)
     */
    private long toLong(Object value) {
        if (value == null) {
            return 0L;
        }
        if (value instanceof Number num) {
            return num.longValue();
        }

        return new BigDecimal(value.toString()).longValue();
    }

    // Repository related methods
    public Optional<Stock> getStock(String ticker) {
        return stockRepo.findByTicker(ticker);
    }

    public Optional<CurrentStockDetails> getCurrentStockDetails(String ticker) {
        return stockDetailsRepo.findByStock_Ticker(ticker);
    }

    public boolean stockExists(String ticker) {
        return stockRepo.existsByTicker(ticker);
    }

}
