package com.div.currencyapi.service.impl;

import com.div.currencyapi.dto.CurrencyDto;
import com.div.currencyapi.entity.CurrencyEntity;
import com.div.currencyapi.entity.ExchangeRateEntity;
import com.div.currencyapi.exception.ExchangeRateNotFoundException;
import com.div.currencyapi.repository.CurrencyRepository;
import com.div.currencyapi.repository.ExchangeRateRepository;
import com.div.currencyapi.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

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

            return (currencyEntityFrom.getRate() / currencyEntityTo.getRate()) * value;
        } else {
            throw new ExchangeRateNotFoundException("Exchange rate not found for the specified criteria!");
        }

    }


    @Override
    @Transactional(rollbackFor = SQLException.class)
    public CurrencyDto createCurrency(CurrencyDto currencyDto) {
        CurrencyEntity currencyEntity = CurrencyEntity.builder().code(currencyDto.getCode())
                .name(currencyDto.getName())
                .rate(currencyDto.getRate())
                .build();
        currencyRepository.save(currencyEntity);


        return CurrencyDto.builder()
                .code(currencyEntity.getCode())
                .name(currencyEntity.getName())
                .rate(currencyEntity.getRate())
                .build();
    }

}
