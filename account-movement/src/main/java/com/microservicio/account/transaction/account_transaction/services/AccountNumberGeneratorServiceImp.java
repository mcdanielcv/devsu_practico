package com.microservicio.account.transaction.account_transaction.services;

import com.microservicio.account.transaction.account_transaction.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountNumberGeneratorServiceImp implements  AccountNumberGeneratorService{

    @Autowired
    private AccountRepository accountRepository;

    private static final int ACCOUNT_NUMBER_LENGTH = 10;

    public String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = generateRandomAccountNumber();
        } while (accountRepository.existsAccountByAccountNumber(Long.parseLong(accountNumber)));
        return accountNumber;
    }

    private String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(ACCOUNT_NUMBER_LENGTH);

        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10); // Genera un dígito del 0 al 9
            sb.append(digit);
        }

        return sb.toString();
    }
}
