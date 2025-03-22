package com.microservicio.account.transaction.account_transaction.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numTransaccion;

    @NotNull
    @Column(unique = true)
    private Long accountNumber;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @NotNull
    private String typeMovement;

    @NotNull
    private double value;

    @NotNull
    private double balance;


}
