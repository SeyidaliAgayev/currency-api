package com.div.currencyapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CurrencyDto {
    private String code;
    private String name;
    private double rate;
}
