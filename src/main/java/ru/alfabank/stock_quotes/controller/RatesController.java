package ru.alfabank.stock_quotes.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alfabank.stock_quotes.service.FeignServiceOERClient;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RatesController {

    private final FeignServiceOERClient feignServiceOERClient;

    @Autowired
    public RatesController(FeignServiceOERClient feignServiceOERClient) {
        this.feignServiceOERClient = feignServiceOERClient;
    }

    @GetMapping("/currencies-json")
    public ResponseEntity<Map<String, String>> getAllRates() {
        return ResponseEntity.ok(feignServiceOERClient.getAllRates());
    }

    @GetMapping("/latest.json")
    public ResponseEntity<JsonNode> getRate(@RequestParam(value = "app_id") String app_id,
                                            @RequestParam(value = "base") String base,
                                            @RequestParam(value = "symbols") String symbols) {
        return ResponseEntity.ok(feignServiceOERClient.getRate(app_id, base, symbols));
    }

    @GetMapping("/historical/{date}.json")
    public ResponseEntity<JsonNode> getRate(@RequestParam("date") String date,
                                            @RequestParam(value = "app_id") String app_id,
                                            @RequestParam(value = "base") String base,
                                            @RequestParam(value = "symbols") String symbols) {
        return ResponseEntity.ok(feignServiceOERClient.getRate(date, app_id, base, symbols));
    }
}
