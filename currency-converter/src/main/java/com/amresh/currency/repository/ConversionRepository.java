package com.amresh.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amresh.currency.entity.ConversionHistory;

public interface ConversionRepository 
        extends JpaRepository<ConversionHistory, Long> {
}