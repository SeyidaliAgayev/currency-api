package com.div.currencyapi.controller;

import com.div.currencyapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/get-exchange-rate")
    public ResponseEntity<Double> getExchangeRate(
            @RequestParam("fromCurrency") String fromCurrency,
            @RequestParam("toCurrency") String toCurrency,
            @RequestParam("value") double value) {
        double exchangeRate = currencyService.getExchangeRate(fromCurrency, toCurrency, value);
        return ResponseEntity.ok(exchangeRate);
    }
}
