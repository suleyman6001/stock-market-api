package com.suleyman.stock_market_api.repository;

import com.suleyman.stock_market_api.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findByTicker(String ticker);
    boolean existsByTicker(String ticker);
}
