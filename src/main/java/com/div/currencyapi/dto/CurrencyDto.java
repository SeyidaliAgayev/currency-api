package com.div.currencyapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class CurrencyDto {
    private LocalDate date;
    private String code;
    private String name;
    private double rate;
}
