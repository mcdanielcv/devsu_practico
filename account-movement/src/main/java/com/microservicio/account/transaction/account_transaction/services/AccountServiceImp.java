package com.microservicio.account.transaction.account_transaction.services;

import com.microservicio.account.transaction.account_transaction.configuracion.RabbitMQConfig;
import com.microservicio.account.transaction.account_transaction.entities.Account;
import com.microservicio.account.transaction.account_transaction.entities.Transaction;
import com.microservicio.account.transaction.account_transaction.exceptions.*;
import com.microservicio.account.transaction.account_transaction.models.AccountDTO;
import com.microservicio.account.transaction.account_transaction.models.AccountDtoRed;
import com.microservicio.account.transaction.account_transaction.models.ReportDTO;
import com.microservicio.account.transaction.account_transaction.repositories.AccountRepository;
import com.microservicio.account.transaction.account_transaction.repositories.TransactionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private ClientApiService clientApiService;

    @Autowired
    private AccountNumberGeneratorService accountNumberGeneratorService;

    @Transactional(readOnly = true)
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            throw new NoAccountsFoundException("No accounts were found in the database.");
        }
        return accounts.stream().map(account -> AccountMapper.INSTANCE.AccountToDto(account)).collect(Collectors.toList());
    }

    @Transactional
    public AccountDTO saveAccount(AccountDTO accountDto) {
        try {
            if (accountRepository.existsAccountByAccountNumber(accountDto.getAccountNumber())) {
                throw new AccountAlreadyExistsException("La Cuenta ya existe");
            }
            accountDto.setAvailableBalance(accountDto.getInitialBalance());
            Account account = AccountMapper.INSTANCE.dtoToAccount(accountDto);
            return AccountMapper.INSTANCE.AccountToDto(accountRepository.save(account));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("The Account already exists with card Id: " + accountDto.getAccountNumber());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional
    public AccountDTO updateAccount(AccountDTO accountDto, Long id) {
        try {
            Optional<Account> cuentaOptional = accountRepository.findById(id);
            Account accountDb = cuentaOptional.orElseThrow(() ->
                    new AccountNotFoundException("Account no encontrado con ID: " + id));

            accountDb.setAccountType(accountDto.getAccountType());
            accountDb.setInitialBalance(accountDto.getInitialBalance());
            accountDb.setState(accountDto.getState());
            return AccountMapper.INSTANCE.AccountToDto(accountRepository.save(accountDb));
        } catch (AccountNotFoundException ex) {
            throw new AccountNotFoundException(ex.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional
    public AccountDtoRed deleteAccountById(Long id) {
        try {
            Optional<Account> cuentaOptional = accountRepository.findById(id);
            Account accountDb = cuentaOptional.orElseThrow(() ->
                    new AccountNotFoundException("Account no encontrado con ID: " + id));
            accountRepository.deleteById(id);
            return AccountMapper.INSTANCE.AccountToDtoRed(accountDb);
        } catch (AccountNotFoundException ex) {
            throw new AccountNotFoundException(ex.getMessage());
        } catch (RuntimeException e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<ReportDTO> generateReportByClientDateRange(Long clientId, Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/M/yyyy");

        Date startDateVo = startDate;
        Date endDateVo = endDate;

        List<Account> accounts = accountRepository.findClientByid(clientId);
        accounts.sort(Comparator.comparing(Account::getAccountNumber));

        return accounts.stream().map(account -> {
            List<Transaction> movements = transactionsRepository.findByCuentaIdAndFechaBetween(account.getAccountNumber(),
                    startDateVo, endDateVo);
            movements.sort(Comparator.comparing(Transaction::getTransactionDate));

            return movements.stream().map(movement -> {
                ReportDTO reportDTO = new ReportDTO();
                reportDTO.setNameClient(clientApiService.getNameClientById(clientId));
                reportDTO.setTransactionDate(formato.format(movement.getTransactionDate()));
                reportDTO.setAccountNumber(account.getAccountNumber().toString());
                reportDTO.setType(account.getAccountType());
                reportDTO.setInitialBalance(account.getInitialBalance());
                reportDTO.setTransactionValue(movement.getValue());
                reportDTO.setAvailableBalance(movement.getBalance());
                reportDTO.setState(account.getState());
                return reportDTO;
            }).collect(Collectors.toList());
        }).flatMap(List::stream).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReportDTO> generateReportDateRange(Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/M/yyyy");

        List<Integer> idClients = clientApiService.getAllIdClients();
        List<Long> idClientsLong = idClients.stream().map(Integer::longValue).collect(Collectors.toList());

        return idClientsLong.stream().map(
                idClient -> {
                    List<Account> accounts = accountRepository.findClientByid(idClient);
                    return accounts.stream().map(account -> {
                        List<Transaction> movements = transactionsRepository.findByCuentaIdAndFechaBetween(account.getAccountNumber(),
                                startDate, endDate);
                        return movements.stream().map(movement -> {
                            ReportDTO reportDTO = new ReportDTO();
                            reportDTO.setNameClient(clientApiService.getNameClientById(idClient));
                            reportDTO.setTransactionDate(formato.format(movement.getTransactionDate()));
                            reportDTO.setAccountNumber(account.getAccountNumber().toString());
                            reportDTO.setType(account.getAccountType());
                            reportDTO.setInitialBalance(account.getInitialBalance());
                            reportDTO.setTransactionValue(movement.getValue());
                            reportDTO.setAvailableBalance(movement.getBalance());
                            return reportDTO;
                        }).collect(Collectors.toList());
                    }).flatMap(List::stream).collect(Collectors.toList());
                }).flatMap(List::stream).collect(Collectors.toList());
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_CLIENT)
    public void receiveMessage(Long clientId) {
        System.out.println("Received Message from Cliente Queue: " + clientId);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setClientId(clientId);
        accountDTO.setAccountType("Ahorros");
        accountDTO.setState("true");
        accountDTO.setInitialBalance(0);
        accountDTO.setAvailableBalance(0);
        accountDTO.setAccountNumber(Long.parseLong(accountNumberGeneratorService.generateAccountNumber()));
        accountDTO = saveAccount(accountDTO);
        log.info(String.format("Cuenta creada:: Cliente->%s , Cuenta->%s", clientId, accountDTO.getAccountNumber()));
    }
}
