package com.finxsoft.rinhabackend.repository;

import com.finxsoft.rinhabackend.domain.Transaction;
import com.finxsoft.rinhabackend.dto.TransactionDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author finx
 */
@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, Long> {
    Flux<Transaction> findFirst10ByClientIdOrderByDateTimeDesc(Long id);

}

