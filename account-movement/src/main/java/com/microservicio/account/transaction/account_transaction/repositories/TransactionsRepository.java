package com.microservicio.account.transaction.account_transaction.repositories;


import com.microservicio.account.transaction.account_transaction.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT a from Transaction a  where a.accountNumber=?1 and a.transactionDate between ?2 and ?3")
    List<Transaction> findByCuentaIdAndFechaBetween(Long cuentaId, Date fechaInicio, Date fechaFin);
}
