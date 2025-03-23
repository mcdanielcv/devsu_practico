package com.microservicio.account.transaction.account_transaction.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountInputDTO {
    @NotNull(message = "The account Number must not be blank")
    private Long accountNumber;
    @NotBlank(message = "The account Type must not be blank")
    private String accountType;
    @NotNull(message = "The initialBalance must not be blank")
    @Min(value = 0, message = "El saldo inicial debe ser mayor o igual a cero.")
    private double initialBalance;
    @NotNull(message = "The available Balance must not be blank")
    private double availableBalance;
    @NotBlank(message = "The state must not be blank")
    private boolean state;
    @NotNull(message = "The client Id Number must not be blank")
    private Long clientId;
}
