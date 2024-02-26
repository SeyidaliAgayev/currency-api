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
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ExchangeRateRepository exchangeRateRepository;
    @Override
    public double getExchangeRate(String fromCurrency, String toCurrency, double value) {
        Optional<ExchangeRateEntity> optionalExchangeRate = exchangeRateRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);


        if (optionalExchangeRate.isPresent()) {
            ExchangeRateEntity exchangeRateEntity = optionalExchangeRate.get();
            return exchangeRateEntity.getRate() * value;
        } else {
            throw new ExchangeRateNotFoundException("Exchange rate not found for the specified criteria!");
        }
    }


    @Override
    @Transactional(rollbackFor = SQLException.class)
    public CurrencyDto createCurrency(CurrencyDto currencyDto) {
        CurrencyEntity currencyEntity = CurrencyEntity.builder().code(currencyDto.getCode())
                .name(currencyDto.getName())
                .rate(currencyDto.getRate()).build();
        currencyRepository.save(currencyEntity);
        return null;
    }

}
