package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.dto.ClientDTO;
import com.finxsoft.rinhabackend.exception.ClientNotFoundException;
import com.finxsoft.rinhabackend.mapper.ClientMapper;
import com.finxsoft.rinhabackend.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * @author finx
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Transactional
    public Mono<ClientDTO> save(ClientDTO clientDTO) {
        return Mono.just(clientMapper.toEntity(clientDTO))
                .flatMap(clientRepository::save)
                .map(clientMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Mono<ClientDTO> findById(Long clientId) {
        return clientRepository
                .findById(clientId)
                .map(clientMapper::toDTO)
                .switchIfEmpty(Mono.error(ClientNotFoundException::new));
    }

}
