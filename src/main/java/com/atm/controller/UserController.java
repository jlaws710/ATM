package com.atm.controller;

import com.atm.model.Account;
import com.atm.model.Users;
import com.atm.repository.AccountRepository;
import com.atm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody Users user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already taken";
        }
        Users users = new Users();
        users.setUsername(user.getUsername());
        users.setPassword(passwordEncoder.encode(user.getPassword()));

        Account account = new Account();
        account.setAccountNumber("ACC" + System.currentTimeMillis());
        account.setBalance(new BigDecimal("0.00"));

        userRepository.save(users);
        accountRepository.save(account);

        return "User registered successfully with account number " + account.getAccountNumber();
    }
}
