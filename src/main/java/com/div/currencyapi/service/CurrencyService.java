package com.div.currencyapi.service;

import com.div.currencyapi.dto.CurrencyDto;
import com.div.currencyapi.entity.CurrencyEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    double getExchangeRate(String fromCurrency, String toCurrency, double value);
    CurrencyDto createCurrency(CurrencyDto currencyDto);
}
