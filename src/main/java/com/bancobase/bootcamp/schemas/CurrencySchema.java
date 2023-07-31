package com.bancobase.bootcamp.schemas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currency")
public class CurrencySchema {
    @Id
    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "value", nullable = false)
    private Double value = 0.0;
}
