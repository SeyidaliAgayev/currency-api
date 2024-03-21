package com.div.currencyapi;

import com.div.currencyapi.dto.ValCurs;
import com.div.currencyapi.dto.Valute;
import com.div.currencyapi.service.CurrencyService;
import com.div.currencyapi.service.impl.CurrencyServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CurrencyApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyApiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        CurrencyServiceImpl currencyService = new CurrencyServiceImpl();
//
//        ValCurs valCursUSD = currencyService.getValuteFromUrl("03.11.2024");
//        ValCurs valCursEUR = currencyService.getValuteFromUrl("03.11.2024");
//        Valute fromCurrency = currencyService.getValuteFromValcurs(valCursUSD, "USD");
//        Valute toCurrency = currencyService.getValuteFromValcurs(valCursEUR, "EUR");
//        double value = 100;
//
//        double exchangeRate = (fromCurrency.getValue() / toCurrency.getValue()) * value;
//        System.out.println("100 DOLLAR IS " + exchangeRate + " EURO");

    }

}
