package com.div.currencyapi.controller;

import com.div.currencyapi.dto.CurrencyDto;
import com.div.currencyapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/create")
    public ResponseEntity<CurrencyDto> createCurrency(@RequestBody CurrencyDto currencyDto) {
        CurrencyDto createdCurrency = currencyService.createCurrency(currencyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCurrency);
    }


}
