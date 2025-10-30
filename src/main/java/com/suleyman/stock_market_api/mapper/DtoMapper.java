package com.suleyman.stock_market_api.mapper;

import com.suleyman.stock_market_api.dto.CurrentStockDetailsDto;
import com.suleyman.stock_market_api.dto.StockDto;
import com.suleyman.stock_market_api.entity.CurrentStockDetails;
import com.suleyman.stock_market_api.entity.Stock;

public class DtoMapper {
    public static StockDto toDto(Stock stock) {
        StockDto dto = new StockDto();
        dto.setTicker(stock.getTicker());
        dto.setName(stock.getName());
        dto.setPrimaryExchange(stock.getPrimaryExchange());
        dto.setCurrency(stock.getCurrency());
        dto.setActive(stock.getActive());

        return dto;
    }

    public static CurrentStockDetailsDto toDto(CurrentStockDetails curStockDetails) {
        CurrentStockDetailsDto dto = new CurrentStockDetailsDto();

        dto.setTicker(curStockDetails.getStock().getTicker());
        dto.setVolume(curStockDetails.getVolume());
        dto.setStockPriceTimestamp(curStockDetails.getStockPriceTimestamp());
        dto.setDelayedCurrentPrice(curStockDetails.getDelayedCurrentPrice());
        dto.setPriceAtMarketOpen(curStockDetails.getPriceAtMarketOpen());
        dto.setHigh(curStockDetails.getHigh());
        dto.setLow(curStockDetails.getLow());
        dto.setVwap(curStockDetails.getVwap());

        return dto;
    }
}
