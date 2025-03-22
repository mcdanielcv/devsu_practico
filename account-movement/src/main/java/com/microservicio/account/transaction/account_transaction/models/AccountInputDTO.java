package com.microservicio.account.transaction.account_transaction.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountInputDTO {
    @NotBlank(message = "The account Number must not be blank")
    private Long accountNumber;
    @NotBlank(message = "The account Type must not be blank")
    private String accountType;
    @NotNull(message = "The initialBalance must not be blank")
    private double initialBalance;
    @NotNull(message = "The available Balance must not be blank")
    private double availableBalance;
    @NotNull(message = "The state must not be blank")
    private boolean state;
    @NotNull(message = "The client Id Number must not be blank")
    private Long clientId;
}
