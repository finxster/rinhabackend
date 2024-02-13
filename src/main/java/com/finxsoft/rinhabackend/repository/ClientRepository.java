package com.finxsoft.rinhabackend.repository;

import com.finxsoft.rinhabackend.domain.Client;
import com.finxsoft.rinhabackend.domain.Transaction;
import com.finxsoft.rinhabackend.dto.StatementResponseDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author finx
 */
@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Long>, ReactiveSortingRepository<Client, Long> {
}
