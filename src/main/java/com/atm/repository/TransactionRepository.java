package com.atm.repository;

import com.atm.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByAccount_AccountNumber(String accountNumber);
}
