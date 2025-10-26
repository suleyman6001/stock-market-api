package com.suleyman.stock_market_api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "stock")
public class Stock {

    // Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String ticker;

    @Column(name = "primary_exchange")
    private String primaryExchange;

    @Column
    private String currency;

    /**
     * Stores if the stock is actively traded in the stock market or not.
     * The default value is true.
     */
    @Column
    private Boolean active = true;

    // Constructors
    public Stock() {}

    public Stock(String name, String ticker, String primaryExchange, String currency, Boolean active) {
        this.name = name;
        this.ticker = ticker;
        this.primaryExchange = primaryExchange;
        this.currency = currency;
        this.active = active;
    }

    // Getters/Setters
    public Integer getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", ticker='" + ticker + '\'' +
                ", primaryExchange='" + primaryExchange + '\'' +
                ", currency='" + currency + '\'' +
                ", active=" + active +
                '}';
    }
}
