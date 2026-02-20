package com.amresh.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amresh.atm.model.BankAccount;
import com.amresh.atm.model.Transaction;
import com.amresh.atm.repository.BankAccountRepository;
import com.amresh.atm.repository.TransactionRepository;
import com.amresh.atm.dto.ApiResponse;

import java.util.List;

@Service
public class ATMService {

    @Autowired
    private BankAccountRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Deposit
    public ApiResponse deposit(String accountNumber, double amount) {

        BankAccount account = repository.findByAccountNumber(accountNumber);

        if (account == null) {
            return new ApiResponse("Account not found!", 0);
        }

        if (amount <= 0) {
            return new ApiResponse("Amount must be greater than 0", account.getBalance());
        }

        account.setBalance(account.getBalance() + amount);
        repository.save(account);

        transactionRepository.save(
                new Transaction(accountNumber, "DEPOSIT", amount)
        );

        return new ApiResponse("Deposit successful", account.getBalance());
    }

    // Withdraw
    public ApiResponse withdraw(String accountNumber, double amount) {

        BankAccount account = repository.findByAccountNumber(accountNumber);

        if (account == null) {
            return new ApiResponse("Account not found!", 0);
        }

        if (amount <= 0) {
            return new ApiResponse("Amount must be greater than 0", account.getBalance());
        }

        if (account.getBalance() < amount) {
            return new ApiResponse("Insufficient balance!", account.getBalance());
        }

        account.setBalance(account.getBalance() - amount);
        repository.save(account);

        transactionRepository.save(
                new Transaction(accountNumber, "WITHDRAW", amount)
        );

        return new ApiResponse("Withdrawal successful", account.getBalance());
    }

    // Check Balance
    public ApiResponse checkBalance(String accountNumber) {

        BankAccount account = repository.findByAccountNumber(accountNumber);

        if (account == null) {
            return new ApiResponse("Account not found!", 0);
        }

        return new ApiResponse("Balance fetched successfully", account.getBalance());
    }

    // Get Transaction History
    public List<Transaction> getTransactions(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}


