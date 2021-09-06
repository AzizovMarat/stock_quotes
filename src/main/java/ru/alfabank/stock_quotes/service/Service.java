package ru.alfabank.stock_quotes.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    private final RequestSender requestSender;

    @Autowired
    public Service(@Qualifier("oer") RequestSender requestSender) { //OpenExchangeRatesRequestSender
        this.requestSender = requestSender;
    }

    public Map<String, String> getCurrencies() {
        return requestSender.getCurrencies();
    }

    public JsonNode getRate(String firstTicker, String secondTicker) {
        return requestSender.getRate(firstTicker, secondTicker);
    }
    public JsonNode getRate(String date, String firstTicker, String secondTicker) {
        return requestSender.getRate(date, firstTicker, secondTicker);
    }

}
