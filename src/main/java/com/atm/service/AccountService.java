package com.atm.service;

import com.atm.model.Account;
import com.atm.model.Transactions;
import com.atm.repository.AccountRepository;
import com.atm.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public BigDecimal getBalance(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(Account::getBalance)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transactions transactions = new Transactions();
        transactions.setAccount(account);
        transactions.setAmount(amount);
        transactions.setType("Withdraw");
        transactions.setTimestamps(LocalDateTime.now());
        transactionRepository.save(transactions);
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transactions transactions = new Transactions();
        transactions.setAccount(account);
        transactions.setAmount(amount);
        transactions.setType("Deposit");
        transactions.setTimestamps(LocalDateTime.now());
        transactionRepository.save(transactions);
    }

    public List<Transactions> getTransactionHistory(String accountNumber) {
        return transactionRepository.findByAccount_AccountNumber(accountNumber);
    }
}
