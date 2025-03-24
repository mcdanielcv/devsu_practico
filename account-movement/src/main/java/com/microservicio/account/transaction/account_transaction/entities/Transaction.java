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
    @NotNull(message = "The account Number must not be blank")
    private Long accountNumber;
    @NotNull(message = "The transaction Date must not be empty")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @NotNull(message = "The type Movement must not be blank")
    private String typeMovement;
    @NotNull(message = "The value must not be empty")
    private double value;
    @NotNull
    private double balance;
}
