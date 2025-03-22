package com.microservicio.account.transaction.account_transaction.models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionInputDTO {
    @Id
    @NotNull(message = "The numTransaccion must not be blank")
    private Long numTransaccion;
    @NotNull(message = "The account Number must not be blank")
    private Long accountNumber;
    @NotNull(message = "The transaction Date must not be blank")
    private Date transactionDate;
    @NotNull(message = "The type Movement must not be blank")
    private String typeMovement;
    @NotNull(message = "The value must not be blank")
    private double value;
    @NotNull(message = "The balance must not be blank")
    private double balance;
}
