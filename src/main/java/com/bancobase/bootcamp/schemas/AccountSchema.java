package com.bancobase.bootcamp.schemas;

import jakarta.persistence.*;
import lombok.*;
// son como los modelos de laravel
@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountSchema {
    @Id
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private Double balance = 0.0;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerSchema customer;
}
