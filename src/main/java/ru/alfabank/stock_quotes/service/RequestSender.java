package ru.alfabank.stock_quotes.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface RequestSender {

    Map<String, String> getCurrencies();
    JsonNode getRate(String date, String firstTicker, String secondTicker);
    JsonNode getRate(String firstTicker, String secondTicker);

}
