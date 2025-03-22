package com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDtoRed {
    private Long accountNumber;
    private String accountType;


    public AccountDtoRed(Long accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;

    }
}
