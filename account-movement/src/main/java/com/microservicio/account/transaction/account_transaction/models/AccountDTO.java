package com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private Long accountNumber;
    private String accountType;
    private double initialBalance;
    private double availableBalance;
    private boolean state;
    private Long clientId;
}
