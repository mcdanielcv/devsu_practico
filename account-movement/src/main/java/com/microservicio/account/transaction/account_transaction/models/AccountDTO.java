package com.microservicio.account.transaction.account_transaction.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    @NotNull(message = "The account Number must not be empty")
    private Long accountNumber;
    @NotBlank(message = "The account Type must not be blank")
    private String accountType;
    @NotNull(message = "The initialBalance must not be empty")
    @Min(value = 0, message = "El saldo inicial debe ser mayor o igual a cero.")
    private double initialBalance;
    @NotNull(message = "The available Balance must not be empty")
    private double availableBalance;
    @NotBlank(message = "The state must not be blank")
    private String state;
    @NotNull(message = "The client Id Number must not be empty")
    private Long clientId;
}
