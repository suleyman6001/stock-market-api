package com.suleyman.stock_market_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {
    @Value("${stock-api.base-url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(baseUrl).build();
    }
}
