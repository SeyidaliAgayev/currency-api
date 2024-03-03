package com.div.currencyapi.repository;

import com.div.currencyapi.entity.CurrencyEntity;
import com.div.currencyapi.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
//    Optional<ExchangeRateEntity> findByDateAndFromCurrencyAndToCurrency(LocalDate date, String fromCurrency, String toCurrency);
//    Optional<CurrencyEntity> findBy(String fromCurrency, String toCurrency);
}

