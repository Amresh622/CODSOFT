package com.amresh.currency.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_history")
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String baseCurrency;
    private String targetCurrency;
    private Double amount;
    private Double rate;
    private Double convertedAmount;

    private LocalDateTime conversionTime;

    // Getters and Setters

    public Long getId() { return id; }

    public String getBaseCurrency() { return baseCurrency; }
    public void setBaseCurrency(String baseCurrency) { this.baseCurrency = baseCurrency; }

    public String getTargetCurrency() { return targetCurrency; }
    public void setTargetCurrency(String targetCurrency) { this.targetCurrency = targetCurrency; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }

    public Double getConvertedAmount() { return convertedAmount; }
    public void setConvertedAmount(Double convertedAmount) { this.convertedAmount = convertedAmount; }

    public LocalDateTime getConversionTime() { return conversionTime; }
    public void setConversionTime(LocalDateTime conversionTime) { this.conversionTime = conversionTime; }
}