package com.microservicio.account.transaction.account_transaction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @NotBlank
    private String accountType;
    @NotNull
    private double initialBalance;
    @NotNull
    private double availableBalance;
    @NotNull
    private boolean state;
    @NotNull
    private Long clientId;

}
