package com.microservicio.account.transaction.account_transaction.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDTO {

    private Long numTransaccion;
    @NotNull(message = "The account Number must not be blank")
    private Long accountNumber;
    @NotNull(message = "The transaction Date must not be empty")
    private Date transactionDate;
    @NotNull(message = "The type Movement must not be blank")
    private String typeMovement;
    @NotNull(message = "The value must not be empty")
    @Min(value = 1, message = "The value must to be greater than zero")
    private double value;
}
