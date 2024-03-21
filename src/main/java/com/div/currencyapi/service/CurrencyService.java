package com.div.currencyapi.service;

import com.div.currencyapi.dto.ValCurs;
import com.div.currencyapi.dto.Valute;
import com.div.currencyapi.entity.CurrencyEntity;

import java.time.LocalDate;

public interface CurrencyService {
    double getExchangeRate(LocalDate date, String fromCurrency, String toCurrency, double value);
    ValCurs getValuteFromUrl(String date);
    Valute getValuteFromValcurs(ValCurs valCurs, String valuteCode);
    CurrencyEntity valuteToCurrencyEntity(Valute valute);
    void saveCurrencyEntity(CurrencyEntity currencyEntity);
}
