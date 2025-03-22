package com.microservicio.account.transaction.account_transaction.services;

import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.AccountDTO;
import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.AccountDtoRed;
import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.ReportDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface AccountService {

    List<AccountDTO> getAllAccounts();

    AccountDTO saveAccount(AccountDTO accountDto);

    AccountDTO updateAccount(AccountDTO accountDto, Long id);

    AccountDtoRed deleteAccountById(Long id);

    List<ReportDTO> generateReport(Long clientId, String startDate, String endDate) throws ParseException;

    List<ReportDTO> generateReport(Date startDate, Date endDate) throws ParseException;

}
