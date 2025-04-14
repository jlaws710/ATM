package com.atm.controller;

import com.atm.model.Account;
import com.atm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/atm")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/balance/{accountNumber}")
    public BigDecimal getBalance(@PathVariable String accountNumber) {
        return accountService.getBalance(accountNumber);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody Account account) {
        accountService.withdraw(account.getAccountNumber(), account.getBalance());
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody Account account) {
        accountService.deposit(account.getAccountNumber(), account.getBalance());
    }
}
