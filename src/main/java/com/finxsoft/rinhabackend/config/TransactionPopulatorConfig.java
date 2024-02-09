package com.finxsoft.rinhabackend.config;

import com.finxsoft.rinhabackend.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author finx
 */
@Configuration
public class TransactionPopulatorConfig {

    private static final Logger log = LoggerFactory.getLogger(TransactionPopulatorConfig.class);

    private final TransactionRepository transactionRepository;

    public TransactionPopulatorConfig(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostConstruct
    public void populate() {
        log.info("Cleaning and populating transactions database...");
        transactionRepository.deleteAll().block();
    }

}
