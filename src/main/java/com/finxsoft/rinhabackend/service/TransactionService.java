package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.domain.Client;
import com.finxsoft.rinhabackend.domain.Transaction;
import com.finxsoft.rinhabackend.dto.CreateTransactionDTO;
import com.finxsoft.rinhabackend.dto.CreateTransactionResponseDTO;
import com.finxsoft.rinhabackend.dto.TransactionDTO;
import com.finxsoft.rinhabackend.exception.NegativeBalanceException;
import com.finxsoft.rinhabackend.mapper.TransactionMapper;
import com.finxsoft.rinhabackend.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * @author finx
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final ClientService clientService;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, ClientService clientService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.clientService = clientService;
    }

    @Transactional
    public Mono<CreateTransactionResponseDTO> newTransaction(Long clientId, CreateTransactionDTO createTransactionDTO) {
        return clientService.findById(clientId)
                .map(client -> getNewBalance(client, createTransactionDTO))
                .map(this::validateNewBalance)
                .flatMap(client -> createTransaction(client, createTransactionDTO))
                .map(this::createReturn);
    }

    @Transactional(readOnly = true)
    public Flux<TransactionDTO> findLastTransactions(Client client) {
        return transactionRepository.findFirst10ByClientIdOrderByDateTimeDesc(client.getId())
                .map(transactionMapper::toDto);
    }

    private Mono<Client> createTransaction(Client client, CreateTransactionDTO createTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setValue(createTransactionDTO.getValue());
        transaction.setDescription(createTransactionDTO.getDescription());
        transaction.setType(createTransactionDTO.getType());
        transaction.setDateTime(Instant.now());
        transaction.setClient(client);
        return transactionRepository.save(transaction)
                .flatMap(t -> saveClient(client));
    }

    private Mono<Client> saveClient(Client client) {
        return clientService.save(client);
    }

    private CreateTransactionResponseDTO createReturn(Client client) {
        CreateTransactionResponseDTO createTransactionResponseDTO = new CreateTransactionResponseDTO();
        createTransactionResponseDTO.setBalance(client.getBalance());
        createTransactionResponseDTO.setLimit(client.getLimit());
        return createTransactionResponseDTO;
    }

    Client validateNewBalance(Client client) {
        if (client.getLimit() < Math.abs(client.getBalance()) && Math.signum(client.getBalance()) == -1) {
            throw new NegativeBalanceException();
        }
        return client;
    }

    private Client getNewBalance(Client client, CreateTransactionDTO createTransactionDTO) {
        int newBalance;
        if (createTransactionDTO.getType().equalsIgnoreCase("d")) {
            newBalance = client.getBalance() - createTransactionDTO.getValue();
        } else {
            newBalance = client.getBalance() + createTransactionDTO.getValue();
        }
        client.setBalance(newBalance);
        return client;
    }

}
