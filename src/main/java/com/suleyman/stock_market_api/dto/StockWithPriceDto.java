package com.suleyman.stock_market_api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class StockWithPriceDto {

    // Fields
    private String ticker;
    private String name;
    private String currency;
    private String primaryExchange;
    private boolean active;

    private BigDecimal delayedCurrentPrice;
    private BigDecimal priceAtMarketOpen;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal vwap;
    private Long volume;
    private Instant stockPriceTimestamp;

    // Constructors
    public StockWithPriceDto() {
    }

    public StockWithPriceDto(String ticker,
                             String name,
                             String currency,
                             String primaryExchange,
                             boolean active,
                             BigDecimal delayedCurrentPrice,
                             BigDecimal priceAtMarketOpen,
                             BigDecimal high,
                             BigDecimal low,
                             BigDecimal vwap,
                             Long volume,
                             Instant stockPriceTimestamp) {
        this.ticker = ticker;
        this.name = name;
        this.currency = currency;
        this.primaryExchange = primaryExchange;
        this.active = active;
        this.delayedCurrentPrice = delayedCurrentPrice;
        this.priceAtMarketOpen = priceAtMarketOpen;
        this.high = high;
        this.low = low;
        this.vwap = vwap;
        this.volume = volume;
        this.stockPriceTimestamp = stockPriceTimestamp;
    }

    // Methods

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Instant getStockPriceTimestamp() {
        return stockPriceTimestamp;
    }

    public void setStockPriceTimestamp(Instant stockPriceTimestamp) {
        this.stockPriceTimestamp = stockPriceTimestamp;
    }

    @Override
    public String toString() {
        return "StockWithPriceDto{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", primaryExchange='" + primaryExchange + '\'' +
                ", active=" + active +
                ", delayedCurrentPrice=" + delayedCurrentPrice +
                ", priceAtMarketOpen=" + priceAtMarketOpen +
                ", high=" + high +
                ", low=" + low +
                ", vwap=" + vwap +
                ", volume=" + volume +
                ", stockPriceTimestamp=" + stockPriceTimestamp +
                '}';
    }
}

