package ru.alfabank.stock_quotes.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "OERClient", url = "https://openexchangerates.org/api")
public interface FeignServiceOERClient {

    @GetMapping(value = "/currencies.json")
    Map<String, String> getAllRates();

    @GetMapping(value = "/latest.json")
    JsonNode getRate(@RequestParam("app_id") String app_id,
                     @RequestParam("base") String base,
                     @RequestParam("symbols") String symbols);

    @GetMapping(value = "/historical/{date}.json")
    JsonNode getRate(@RequestParam("date") String date,
                     @RequestParam("app_id") String app_id,
                     @RequestParam("base") String base,
                     @RequestParam("symbols") String symbols);
}
