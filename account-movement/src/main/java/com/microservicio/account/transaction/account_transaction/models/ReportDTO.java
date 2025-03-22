package com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models;



public class ReportDTO {
    private String transactionDate;
    private String nameClient;
    private String accountNumber;
    private String type;
    private Double initialBalance;
    private Boolean state;
    private Double transactionValue;
    private Double availableBalance;

    public ReportDTO() {

    }

    public ReportDTO(String transactionDate, String nameClient, String accountNumber,
                     String type, Double initialBalance, Boolean state, Double transactionValue,
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }
}
