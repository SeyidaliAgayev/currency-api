package com.div.currencyapi.service.impl;

import com.div.currencyapi.dto.ValCurs;
import com.div.currencyapi.dto.Valute;
import com.div.currencyapi.entity.CurrencyEntity;
import com.div.currencyapi.exception.ExchangeRateNotFoundException;
import com.div.currencyapi.repository.CurrencyRepository;
import com.div.currencyapi.repository.ExchangeRateRepository;
import com.div.currencyapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ExchangeRateRepository exchangeRateRepository;
    @Override
    public double getExchangeRate(LocalDate date, String fromCurrency, String toCurrency, double value) {
        Optional<CurrencyEntity> fromCurrencyCode = currencyRepository.findByCode(fromCurrency);
        Optional<CurrencyEntity> toCurrencyCode = currencyRepository.findByCode(toCurrency);

        if (fromCurrencyCode.isPresent() & toCurrencyCode.isPresent()) {
            CurrencyEntity currencyEntityFrom = fromCurrencyCode.get();
            CurrencyEntity currencyEntityTo = toCurrencyCode.get();

            if (currencyEntityFrom.getName().equals("AZN") || currencyEntityTo.getName().equals("AZN")) {
                return currencyEntityTo.getRate();
            }
            return (currencyEntityFrom.getRate() / currencyEntityTo.getRate()) * value;
        } else {
            throw new ExchangeRateNotFoundException("Exchange rate not found for the specified criteria!");
        }

    }



    @Override
    public ValCurs getValuteFromUrl(String date) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("https://cbar.az/currencies/" + date + ".xml");


        ValCurs valcurs = restTemplate.getForObject("https://cbar.az/currencies/" + date+ ".xml", ValCurs.class);
        return valcurs;
    }

    @Override
    public CurrencyEntity valuteToCurrencyEntity(Valute valute) {
        return CurrencyEntity.builder()
                .code(valute.getCode())
                .name(valute.getName())
                .rate(valute.getValue())
                .build();
    }

    @Override
    public void saveCurrencyEntity(CurrencyEntity currencyEntity) {
        currencyRepository.save(currencyEntity);
    }

    public Valute getValuteFromValcurs(ValCurs valCurs, String valuteCode) {
        return valCurs.getValTypes().stream()
                .flatMap(valType -> valType.getValutes().stream())
                .filter(valute -> valute.getCode().equalsIgnoreCase(valuteCode))
                .findFirst()
                .orElse(null);
    }

}
