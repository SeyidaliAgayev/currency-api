package com.div.currencyapi.controller;

import com.div.currencyapi.exception.ExchangeRateNotFoundException;
import com.div.currencyapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> getExchangeRate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("currency") String currency,
            @RequestParam("exchangeCurrency") String exchangeCurrency,
            @RequestParam("value") double value) {

        try {
            double result = currencyService.getExchangeRate(date, currency, exchangeCurrency, value);
            return ResponseEntity.ok(result);
        } catch (ExchangeRateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
