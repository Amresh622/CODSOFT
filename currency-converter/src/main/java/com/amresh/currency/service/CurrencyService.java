package com.amresh.currency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amresh.currency.dto.CurrencyRequest;
import com.amresh.currency.entity.ConversionHistory;
import com.amresh.currency.repository.ConversionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {

    @Autowired
    private ConversionRepository repository;

    public ConversionHistory convertCurrency(CurrencyRequest request) {

        try {

            String base = request.getBaseCurrency().toUpperCase();
            String target = request.getTargetCurrency().toUpperCase();

            String url = "https://open.er-api.com/v6/latest/" + base;

            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response == null || !response.containsKey("rates")) {
                throw new RuntimeException("Invalid response from currency API");
            }

            Map<String, Double> rates = (Map<String, Double>) response.get("rates");

            if (!rates.containsKey(target)) {
                throw new RuntimeException("Target currency not supported");
            }

            Double rate = rates.get(target);
            Double convertedAmount = request.getAmount() * rate;

            ConversionHistory history = new ConversionHistory();
            history.setBaseCurrency(base);
            history.setTargetCurrency(target);
            history.setAmount(request.getAmount());
            history.setRate(rate);
            history.setConvertedAmount(convertedAmount);
            history.setConversionTime(LocalDateTime.now());

            return repository.save(history);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Conversion failed: " + e.getMessage());
        }
    }

    public List<ConversionHistory> getAllHistory() {
        return repository.findAll();
    }
}
        