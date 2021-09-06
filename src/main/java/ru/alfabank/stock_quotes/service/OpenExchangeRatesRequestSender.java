package ru.alfabank.stock_quotes.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("oer")
public class OpenExchangeRatesRequestSender implements RequestSender {

    private final OpenExchangeRatesConfiguration openExchangeRatesConfiguration;
    private final FeignServiceOURClient feignServiceOURClient;

    @Autowired
    public OpenExchangeRatesRequestSender(OpenExchangeRatesConfiguration openExchangeRatesConfiguration,
                                          FeignServiceOURClient feignServiceOURClient) {
        this.openExchangeRatesConfiguration = openExchangeRatesConfiguration;
        this.feignServiceOURClient = feignServiceOURClient;
    }

    @Override
    public Map<String, String> getCurrencies() {
        return ResponseEntity.ok(feignServiceOURClient.getAllRates()).getBody();
    }

    public JsonNode getRate(String firstTicker, String secondTicker) {
        return ResponseEntity.ok(feignServiceOURClient.getRate(openExchangeRatesConfiguration.getAppID(), firstTicker, secondTicker)).getBody();
    }

    public JsonNode getRate(String date, String firstTicker, String secondTicker) {
        return ResponseEntity.ok(feignServiceOURClient.getRate(date, openExchangeRatesConfiguration.getAppID(), firstTicker, secondTicker)).getBody();
    }

}
