package com.suleyman.stock_market_api.service;

import com.suleyman.stock_market_api.entity.CurrentStockDetails;
import com.suleyman.stock_market_api.repository.CurrentStockDetailsRepository;
import com.suleyman.stock_market_api.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepo;
    private final CurrentStockDetailsRepository stockDetailsRepo;
    private final WebClient webClient;

    @Autowired
    public StockService(WebClient webClient, StockRepository stockRepo, CurrentStockDetailsRepository stockDetailsRepo) {
        this.webClient = webClient;
        this.stockRepo = stockRepo;
        this.stockDetailsRepo = stockDetailsRepo;
    }

    /**
     * This method returns the price details of the stock with the given ticker
     * @param ticker exchange name of the stock
     * @return Optional that can contain the current price details of the stock
     */
    public Optional<CurrentStockDetails> getCurrentPriceDetails(String ticker) {
        return stockDetailsRepo.findByStock_Ticker(ticker);
    }

    /**
     * This method checks if the stock with the given ticker is in the DB or not
     * @param ticker exchange name of the stock
     * @return true if stock exists, false otherwise
     */
    public boolean stockExists(String ticker) {
        return stockRepo.existsByTicker(ticker);
    }

    public CurrentStockDetails fetchAndSaveStockPriceDetails(String ticker) {
        // Will be implemented during controller implementation
        return null;
    }
}
