package com.suleyman.stock_market_api.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String ticker) {
        super("Stock with ticker '" + ticker + "' not found in external API or database");
    }
}
