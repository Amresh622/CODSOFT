package com.amresh.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amresh.currency.dto.CurrencyRequest;
import com.amresh.currency.entity.ConversionHistory;
import com.amresh.currency.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@CrossOrigin("*")
public class CurrencyController {

    @Autowired
    private CurrencyService service;

    // Convert Currency
    @PostMapping("/convert")
    public ResponseEntity<ConversionHistory> convert(
            @RequestBody CurrencyRequest request) {

        return ResponseEntity.ok(service.convertCurrency(request));
    }

    // Get All Conversion History
    @GetMapping("/history")
    public ResponseEntity<List<ConversionHistory>> getHistory() {
        return ResponseEntity.ok(service.getAllHistory());
    }
}
