package com.suleyman.stock_market_api.controller;

import com.suleyman.stock_market_api.dto.StockWithPriceDto;
import com.suleyman.stock_market_api.entity.CurrentStockDetails;
import com.suleyman.stock_market_api.entity.Stock;
import com.suleyman.stock_market_api.service.StockService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // GET /api/stocks/NVDA
    @GetMapping("/{ticker}")
    public ResponseEntity<StockWithPriceDto> getStockWithPrice(@PathVariable String ticker) {
        try {
            StockWithPriceDto dto = stockService.getStockWithLatestPrice(ticker);
            return ResponseEntity.ok(dto);
        }
        catch (Exception e) {
            logger.error("Failed to get stock {}: {}", ticker, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
