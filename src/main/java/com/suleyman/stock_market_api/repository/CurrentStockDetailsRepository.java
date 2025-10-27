package com.suleyman.stock_market_api.repository;

import com.suleyman.stock_market_api.entity.CurrentStockDetails;
import com.suleyman.stock_market_api.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CurrentStockDetailsRepository extends JpaRepository<CurrentStockDetails, Integer> {
    Optional<CurrentStockDetails> findByStock(Stock stock);
    Optional<CurrentStockDetails> findByStock_Ticker(String ticker);
    Optional<CurrentStockDetails> findByStock_Id(Integer stockId);
}
