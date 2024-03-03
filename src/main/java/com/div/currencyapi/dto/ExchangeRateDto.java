package com.div.currencyapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@Builder
public class ExchangeRateDto {
    private String fromCurrency;
    private String toCurrency;
    private double rate;
}
