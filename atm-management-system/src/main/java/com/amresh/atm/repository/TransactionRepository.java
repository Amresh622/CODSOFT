package com.amresh.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amresh.atm.model.Transaction;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumber(String accountNumber);
}

