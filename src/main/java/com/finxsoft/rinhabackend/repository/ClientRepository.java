package com.finxsoft.rinhabackend.repository;

import com.finxsoft.rinhabackend.domain.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author finx
 */
@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, Long> {
}
