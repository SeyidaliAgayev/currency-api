package com.div.currencyapi.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "currencies", schema = "currencies_api")
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "currency_code")
    private String code;

    @Column(name = "exchange_date")
    LocalDate date;

    @Column(name = "currency_name")
    private String name;

    @Column(name = "rate")
    private double rate;

    @OneToMany(mappedBy = "sourceCurrency", cascade = CascadeType.ALL)
    private Set<ExchangeRateEntity> exchangeRates;
}

