package com.div.currencyapi.exception;

public class ExchangeRateNotFoundException extends RuntimeException{
    public ExchangeRateNotFoundException(String message) {
        super(message);
    }
}