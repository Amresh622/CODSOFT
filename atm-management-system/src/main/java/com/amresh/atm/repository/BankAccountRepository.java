package com.amresh.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amresh.atm.model.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount findByAccountNumber(String accountNumber);
}

