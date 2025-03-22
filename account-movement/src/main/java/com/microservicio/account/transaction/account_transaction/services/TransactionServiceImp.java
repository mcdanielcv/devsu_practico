package com.microservicio.account.transaction.account_transaction.services;

import com.microservicio.account.transaction.account_transaction.entities.Account;
import com.microservicio.account.transaction.account_transaction.entities.Transaction;
import com.microservicio.account.transaction.account_transaction.exceptions.AccountNotFoundException;
import com.microservicio.account.transaction.account_transaction.exceptions.InsufficientFundsException;
import com.microservicio.account.transaction.account_transaction.exceptions.InternalServerException;
import com.microservicio.account.transaction.account_transaction.exceptions.NoTransactionsFoundException;
import com.microservicio.account.transaction.account_transaction.repositories.AccountRepository;
import com.microservicio.account.transaction.account_transaction.repositories.TransactionsRepository;
import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactionList = transactionsRepository.findAll();
        if (transactionList.isEmpty()) {
            throw new NoTransactionsFoundException("No transactions were found in the database.");
        }
        return transactionList.stream().map(transaction -> TransactionMapper.INSTANCE.transactionToDto(transaction)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Transaction> getTransactionById(@NonNull Long id) {
        return transactionsRepository.findById(id);
    }

    @Transactional
    public TransactionDTO saveTransaction(TransactionDTO transactionDto) {
        try {
            Optional<Account> accountOptional = accountRepository.findById(transactionDto.getAccountNumber());
            Account accountDb = accountOptional.orElseThrow(() ->
                    new AccountNotFoundException("Account not found withn Account number: " + transactionDto.getAccountNumber()));

            if (transactionDto.getValue() < 0 && accountDb.getAvailableBalance() < Math.abs(transactionDto.getValue())) {
                throw new InsufficientFundsException("Balance not available in the account.");
            }
            accountDb.setAvailableBalance(accountDb.getInitialBalance() + (1 * transactionDto.getValue()));
            Transaction transaction = TransactionMapper.INSTANCE.dtoToTransaction(transactionDto);
            transaction.setBalance(accountDb.getAvailableBalance());
            Transaction savedMovimiento = transactionsRepository.save(transaction);
            accountRepository.save(accountDb);
            return TransactionMapper.INSTANCE.transactionToDto(savedMovimiento);
        } catch (AccountNotFoundException ex) {
            throw new AccountNotFoundException(ex.getMessage());
        } catch (InsufficientFundsException e) {
            throw new InsufficientFundsException(e.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional
    public TransactionDTO deleteTransactionById(Long id) {
        try {
            Optional<Transaction> movimientoOptional = transactionsRepository.findById(id);
            Transaction transactionDb = movimientoOptional.orElseThrow(() ->
                    new NoTransactionsFoundException("transaction not found with the ID: " + id));
            Optional<Account> cuentaOptional = accountRepository
                    .findById(transactionDb.getAccountNumber());
            Account accountDb = cuentaOptional.orElseThrow(() ->
                    new AccountNotFoundException("Account not found with the Account Number: " + id + " to perform the movement"));

            accountDb.setAvailableBalance(accountDb.getInitialBalance() + ((-1) * movimientoOptional.get().getValue()));
            accountRepository.save(accountDb);
            transactionsRepository.deleteById(id);
            return TransactionMapper.INSTANCE.transactionToDto(movimientoOptional.get());
        } catch (AccountNotFoundException e) {
            throw new AccountNotFoundException(e.getMessage());
        } catch (NoTransactionsFoundException e) {
            throw new NoTransactionsFoundException(e.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
