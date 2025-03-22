package com.microservicio.account.transaction.account_transaction.repositories;

import com.microservicio.account.transaction.account_transaction.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT COUNT(c) > 0 END FROM Account c WHERE c.accountNumber = :accountNumber")
    boolean existsAccountByAccountNumber(@Param("accountNumber") Long accountNumber);

    @Query("SELECT c FROM Account c WHERE c.clientId = :clientId")
    List<Account> findClientByid(Long clientId);
}
