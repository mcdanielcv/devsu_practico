package com.microservicio.account.transaction.account_transaction.controllers;

import com.microservicio.account.transaction.account_transaction.services.AccountService;
import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.AccountDTO;
import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.ResponseVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        List<AccountDTO> list = accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseVo(true, "", list));
    }

    @PostMapping
    public ResponseEntity<?> saveAccount(@Valid @RequestBody AccountDTO accountDto, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseVo(true, "Cuenta creada", accountService.saveAccount(accountDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccountById(@Valid @RequestBody AccountDTO accountDto, BindingResult result,
                                               @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseVo(true, "Cuenta Actualizado", accountService.updateAccount(accountDto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseVo(true, "Cuenta Borrada", accountService.deleteAccountById(id)));
    }
}
