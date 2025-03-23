package com.microservicio.account.transaction.account_transaction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @Column(unique = true)
    private Long accountNumber;
    @NotBlank(message = "The account Type must not be blank")
    private String accountType;
    @NotNull(message = "The initialBalance must not be blank")
    @Min(value = 0, message = "El saldo inicial debe ser mayor o igual a cero.")
    private double initialBalance;
    @NotNull
    private double availableBalance;
    @NotBlank(message = "The state must not be blank")
    private String state;
    @NotNull
    private Long clientId;

}
