package ru.alfabank.stock_quotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenExchangeRatesInitializer {

    private final OpenExchangeRatesConfiguration openExchangeRatesConfiguration;

    @Autowired
    public OpenExchangeRatesInitializer(OpenExchangeRatesConfiguration openExchangeRatesConfiguration) {
        this.openExchangeRatesConfiguration = openExchangeRatesConfiguration;
    }
}
