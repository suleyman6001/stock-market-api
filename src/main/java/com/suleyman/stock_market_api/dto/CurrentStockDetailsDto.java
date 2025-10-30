package com.suleyman.stock_market_api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class CurrentStockDetailsDto {

    // Fields
    private String ticker;
    private Instant stockPriceTimestamp;
    private Long volume;
    private BigDecimal delayedCurrentPrice;
    private BigDecimal priceAtMarketOpen;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal vwap;

    // Constructors
    public CurrentStockDetailsDto() {
    }

    public CurrentStockDetailsDto(String ticker, Instant stockPriceTimestamp, Long volume, BigDecimal delayedCurrentPrice, BigDecimal priceAtMarketOpen, BigDecimal high, BigDecimal low, BigDecimal vwap) {
        this.ticker = ticker;
        this.stockPriceTimestamp = stockPriceTimestamp;
        this.volume = volume;
        this.delayedCurrentPrice = delayedCurrentPrice;
        this.priceAtMarketOpen = priceAtMarketOpen;
        this.high = high;
        this.low = low;
        this.vwap = vwap;
    }

    // Methods
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Instant getStockPriceTimestamp() {
        return stockPriceTimestamp;
    }

    public void setStockPriceTimestamp(Instant stockPriceTimestamp) {
        this.stockPriceTimestamp = stockPriceTimestamp;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public BigDecimal getDelayedCurrentPrice() {
        return delayedCurrentPrice;
    }

    public void setDelayedCurrentPrice(BigDecimal delayedCurrentPrice) {
        this.delayedCurrentPrice = delayedCurrentPrice;
    }

    public BigDecimal getPriceAtMarketOpen() {
        return priceAtMarketOpen;
    }

    public void setPriceAtMarketOpen(BigDecimal priceAtMarketOpen) {
        this.priceAtMarketOpen = priceAtMarketOpen;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getVwap() {
        return vwap;
    }

    public void setVwap(BigDecimal vwap) {
        this.vwap = vwap;
    }

    @Override
    public String toString() {
        return "CurrentStockDetailsDto{" +
                "ticker='" + ticker + '\'' +
                ", stockPriceTimestamp=" + stockPriceTimestamp +
                ", volume=" + volume +
                ", delayedCurrentPrice=" + delayedCurrentPrice +
                ", priceAtMarketOpen=" + priceAtMarketOpen +
                ", high=" + high +
                ", low=" + low +
                ", vwap=" + vwap +
                '}';
    }
}
