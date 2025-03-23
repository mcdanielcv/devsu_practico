package com.microservicio.account.transaction.account_transaction.services;

import com.microservicio.account.transaction.account_transaction.entities.Transaction;
import com.microservicio.account.transaction.account_transaction.models.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "accountNumber", target = "accountNumber")
    Transaction dtoToTransaction(TransactionDTO transactionDto);

    @Mapping(source = "accountNumber", target = "accountNumber")
    TransactionDTO transactionToDto(Transaction transaction);
}
