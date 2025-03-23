package com.microservicio.account.transaction.account_transaction.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDTO {
    private String transactionDate;
    private String nameClient;
    private String accountNumber;
    private String type;
    private Double initialBalance;
    private String state;
    private Double transactionValue;
    private Double availableBalance;

    public ReportDTO() {

    }

    public ReportDTO(String transactionDate, String nameClient, String accountNumber,
                     String type, Double initialBalance, String state, Double transactionValue,
                     Double availableBalance) {
        this.transactionDate = transactionDate;
        this.nameClient = nameClient;
        this.accountNumber = accountNumber;
        this.type = type;
        this.initialBalance = initialBalance;
        this.state = state;
        this.transactionValue = transactionValue;
        this.availableBalance = availableBalance;
    }
}
