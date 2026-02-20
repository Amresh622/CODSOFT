package com.amresh.atm.dto;

public class ApiResponse {

    private String message;
    private double balance;

    public ApiResponse(String message, double balance) {
        this.message = message;
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public double getBalance() {
        return balance;
    }
}
