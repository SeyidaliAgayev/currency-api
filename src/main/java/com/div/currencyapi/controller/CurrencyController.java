package com.div.currencyapi.controller;

import com.div.currencyapi.dto.ValCurs;
import com.div.currencyapi.dto.Valute;
import com.div.currencyapi.entity.CurrencyEntity;
import com.div.currencyapi.exception.ExchangeRateNotFoundException;
import com.div.currencyapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/api/v1/currency")
@CrossOrigin

public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;
    @PostMapping()
    public ResponseEntity<Double> createCurrency(@RequestParam("currency") String fromCurrencyCode,
                                                 @RequestParam("exchangeCurrency") String toCurrencyCode,
                                                 @RequestParam("value") double value,
                                                 @RequestParam(name = "date") String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String formattedDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            ValCurs valCursFromCurrency = currencyService.getValuteFromUrl(formattedDate);
            ValCurs valCursToCurrency = currencyService.getValuteFromUrl(formattedDate);

            Valute fromCurrency = currencyService.getValuteFromValcurs(valCursFromCurrency, fromCurrencyCode);
            Valute toCurrency = currencyService.getValuteFromValcurs(valCursToCurrency, toCurrencyCode);

            CurrencyEntity currencyEntityFrom = currencyService.valuteToCurrencyEntity(fromCurrency);
            CurrencyEntity currencyEntityTo = currencyService.valuteToCurrencyEntity(toCurrency);

            currencyService.saveCurrencyEntity(currencyEntityFrom);
            currencyService.saveCurrencyEntity(currencyEntityTo);

            double exchangeRate = (fromCurrency.getValue() / toCurrency.getValue()) * value;

            return ResponseEntity.ok(exchangeRate);
        } catch (ExchangeRateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Double.valueOf(e.getMessage()));
        }
    }
}
