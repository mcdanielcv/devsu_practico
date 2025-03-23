package com.microservicio.account.transaction.account_transaction.services;

import com.microservicio.account.transaction.account_transaction.entities.Transaction;
import com.microservicio.account.transaction.account_transaction.models.TransactionDTO;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    List<TransactionDTO> getAllTransactions();

    Optional<Transaction> getTransactionById(@NonNull Long id);

    TransactionDTO saveTransaction(TransactionDTO transactionVo);

    TransactionDTO deleteTransactionById(Long id);

}
