package com.div.currencyapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "exchange_rates", schema = "currencies_api")
public class ExchangeRateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "target_currency_code")
    private String currency;

    @Column(name = "exchange_rate")
    private double rate;

    @ManyToOne
    @JoinColumn(name = "source_currency_id")
    private CurrencyEntity sourceCurrency;
}

