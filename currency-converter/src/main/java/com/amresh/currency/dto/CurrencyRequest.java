package com.amresh.currency.dto;

public class CurrencyRequest {

    private String baseCurrency;
    private String targetCurrency;
    private Double amount;

    public String getBaseCurrency() { return baseCurrency; }
    public void setBaseCurrency(String baseCurrency) { this.baseCurrency = baseCurrency; }

    public String getTargetCurrency() { return targetCurrency; }
    public void setTargetCurrency(String targetCurrency) { this.targetCurrency = targetCurrency; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
