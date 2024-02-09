package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.domain.Client;
import com.finxsoft.rinhabackend.dto.BalanceDTO;
import com.finxsoft.rinhabackend.dto.StatementResponseDTO;
import com.finxsoft.rinhabackend.dto.TransactionDTO;
import com.finxsoft.rinhabackend.exception.ClientNotFoundException;
import com.finxsoft.rinhabackend.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author finx
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Mono<Client> save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Mono<Client> findById(Long clientId) {
        return clientRepository
                .findById(clientId)
                .switchIfEmpty(Mono.error(ClientNotFoundException::new));
    }
}
