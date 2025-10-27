package com.suleyman.stock_market_api;

import com.suleyman.stock_market_api.entity.CurrentStockDetails;
import com.suleyman.stock_market_api.entity.Stock;
import com.suleyman.stock_market_api.repository.CurrentStockDetailsRepository;
import com.suleyman.stock_market_api.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@SpringBootApplication
public class StockMarketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketApiApplication.class, args);
	}

	// CommandLineRunner for testing. Remove after the project is done.
	/*@Bean
	@Transactional
	CommandLineRunner demo(StockRepository stocks, CurrentStockDetailsRepository details) {
		return args -> {
			/*Stock s = new Stock();
			s.setTicker("NVDA");
			s.setName("NVIDIA Corporation");
			s.setCurrency("USD");
			s.setPrimaryExchange("NASDAQ");
			s.setActive(true);
			stocks.save(s);

			 */
/*
			System.out.println("exists? " + stocks.existsByTicker("NVDA"));
			System.out.println("find: " + stocks.findByTicker("NVDA"));

			// 1. Load existing NVDA Stock
			Optional<Stock> optionalNvda = stocks.findByTicker("NVDA");

			if (optionalNvda.isEmpty()) {
				System.out.println("NVDA stock not found. Run the first runner first.");
				return;
			}

			Stock nvda = optionalNvda.get();

			// 2. Create CurrentStockDetails object bound to NVDA
			CurrentStockDetails snapshot = new CurrentStockDetails(
					nvda,                                 // one-to-one binding
					Instant.now(),                        // bar timestamp
					1_234_567L,                           // trade volume
					new BigDecimal("185.54"),             // delayed current price
					new BigDecimal("183.83"),             // price at market open
					new BigDecimal("187.47"),             // high
					new BigDecimal("183.50"),             // low
					new BigDecimal("185.54")              // vwap
			);

			// 3. Save snapshot
			details.save(snapshot);

			// 4. Retrieve it back from DB (JOIN under the hood)
			Optional<CurrentStockDetails> fromDb =
					details.findByStock_Ticker("NVDA");

			System.out.println("From DB: " + fromDb);
		};
	}*/
}
