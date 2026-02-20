package com.amresh.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amresh.atm.dto.ApiResponse;
import com.amresh.atm.model.BankAccount;
import com.amresh.atm.model.Transaction;
import com.amresh.atm.repository.BankAccountRepository;
import com.amresh.atm.service.ATMService;

import java.util.List;

@RestController
@RequestMapping("/api/atm")
@CrossOrigin
public class ATMController {

    @Autowired
    private ATMService service;

    @Autowired
    private BankAccountRepository repository;

    // Create Account
    @PostMapping("/create")
    public BankAccount createAccount(@RequestBody BankAccount account) {
        return repository.save(account);
    }

    // Deposit
    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> deposit(
            @RequestParam String accountNumber,
            @RequestParam double amount) {

        return ResponseEntity.ok(service.deposit(accountNumber, amount));
    }

    // Withdraw
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdraw(
            @RequestParam String accountNumber,
            @RequestParam double amount) {

        return ResponseEntity.ok(service.withdraw(accountNumber, amount));
    }

    // Check Balance
    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<ApiResponse> checkBalance(
            @PathVariable String accountNumber) {

        return ResponseEntity.ok(service.checkBalance(accountNumber));
    }

    // Transaction History
    @GetMapping("/transactions/{accountNumber}")
    public List<Transaction> getTransactions(
            @PathVariable String accountNumber) {

        return service.getTransactions(accountNumber);
    }
}
