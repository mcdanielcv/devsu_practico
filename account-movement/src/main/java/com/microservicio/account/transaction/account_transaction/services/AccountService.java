package com.microservicio.account.transaction.account_transaction.services;

import com.microservicio.account.transaction.account_transaction.models.AccountDTO;
import com.microservicio.account.transaction.account_transaction.models.AccountDtoRed;
import com.microservicio.account.transaction.account_transaction.models.ReportDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface AccountService {

    List<AccountDTO> getAllAccounts();

    AccountDTO saveAccount(AccountDTO accountDto);

    AccountDTO updateAccount(AccountDTO accountDto, Long id);

    AccountDtoRed deleteAccountById(Long id);

    List<ReportDTO> generateReportByClientDateRange(Long clientId, Date startDate, Date endDate) throws ParseException;

    List<ReportDTO> generateReportDateRange(Date startDate, Date endDate) throws ParseException;

}
