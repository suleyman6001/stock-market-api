package com.suleyman.stock_market_api.dto;

public class StockDto {

    // Fields
    private String ticker;
    private String name;
    private String primaryExchange;
    private String currency;
    private boolean active = true;

    // Constructors
    public StockDto() {
    }

    public StockDto(String ticker, String name, String primaryExchange, String currency, boolean active) {
        this.ticker = ticker;
        this.name = name;
        this.primaryExchange = primaryExchange;
        this.currency = currency;
        this.active = active;
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

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "StockDto{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", primaryExchange='" + primaryExchange + '\'' +
                ", currency='" + currency + '\'' +
                ", active=" + active +
                '}';
    }
}
