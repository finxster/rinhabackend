package com.finxsoft.rinhabackend.repository;

import com.finxsoft.rinhabackend.domain.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author finx
 */
@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long>, ReactiveSortingRepository<Transaction, Long> {
    Flux<Transaction> findFirst10ByClientIdOrderByDateTimeDesc(Long id);

}

