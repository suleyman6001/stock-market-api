package com.suleyman.stock_market_api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "current_stock_details", uniqueConstraints = @UniqueConstraint(columnNames = "stock_id"))
public class CurrentStockDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    /**
     * Displays the moment the price for that stock was sent by the Polygon API
     */
    @Column(name = "stock_price_timestamp", nullable = false)
    private Instant stockPriceTimestamp;

    /**
     * Trade volume in given interval
     */
    @Column
    private Long volume;

    /**
     * Price at the end of the given interval (bar)
     */
    @Column(name = "current_price", nullable = false, precision = 18, scale = 6)
    private BigDecimal delayedCurrentPrice;

    /**
     * Price at the beginning of the given interval (bar)
     */
    @Column(name = "price_open", precision = 18, scale = 6, nullable = false)
    private BigDecimal priceAtMarketOpen;

    /**
     * Highest price in the given interval (bar)
     */
    @Column(nullable = false, precision = 18, scale = 6)
    private BigDecimal high;

    /**
     * Lowest price in the given interval (bar)
     */
    @Column(precision = 18, scale = 6, nullable = false)
    private BigDecimal low;

    /**
     * VWAP represents Volume-Weighted Average Price
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal vwap;

    // Constructors
    public CurrentStockDetails() {
    }

    public CurrentStockDetails(Stock stock, Instant stockPriceTimestamp, Long volume,
                               BigDecimal delayedCurrentPrice, BigDecimal priceAtMarketOpen,
                               BigDecimal high, BigDecimal low, BigDecimal vwap) {
        this.stock = stock;
        this.stockPriceTimestamp = stockPriceTimestamp;
        this.volume = volume;
        this.delayedCurrentPrice = delayedCurrentPrice;
        this.priceAtMarketOpen = priceAtMarketOpen;
        this.high = high;
        this.low = low;
        this.vwap = vwap;
    }

    // Methods

    public Integer getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public Instant getCreatedAt() {
        return createdAt;
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
        return "CurrentStockDetails{" +
                "id=" + id +
                ", stock=" + (stock != null ? stock.getTicker() : null) +
                ", createdAt=" + createdAt +
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
