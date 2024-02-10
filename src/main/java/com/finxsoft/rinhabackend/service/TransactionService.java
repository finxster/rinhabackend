package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.domain.Transaction;
import com.finxsoft.rinhabackend.dto.ClientDTO;
import com.finxsoft.rinhabackend.dto.CreateTransactionDTO;
import com.finxsoft.rinhabackend.dto.CreateTransactionResponseDTO;
import com.finxsoft.rinhabackend.dto.TransactionDTO;
import com.finxsoft.rinhabackend.exception.InvalidTransactionException;
import com.finxsoft.rinhabackend.exception.NegativeBalanceException;
import com.finxsoft.rinhabackend.mapper.TransactionMapper;
import com.finxsoft.rinhabackend.repository.TransactionRepository;
import io.micrometer.common.util.StringUtils;
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
                .map(client -> validateNewTransaction(client, createTransactionDTO))
                .map(this::validateNewBalance)
                .flatMap(client -> createTransaction(client, createTransactionDTO))
                .map(this::createReturn);
    }

    private ClientDTO validateNewTransaction(ClientDTO clientDTO, CreateTransactionDTO createTransactionDTO) {
        if (StringUtils.isBlank(createTransactionDTO.getDescription())) {
            throw new InvalidTransactionException();
        }
        if (createTransactionDTO.getDescription().length() > 10) {
            throw new InvalidTransactionException();
        }
        return clientDTO;
    }

    @Transactional(readOnly = true)
    public Flux<TransactionDTO> findLastTransactions(ClientDTO clientDTO) {
        return transactionRepository.findFirst10ByClientIdOrderByDateTimeDesc(clientDTO.getId())
                .map(transactionMapper::toDto);
    }

    private Mono<ClientDTO> createTransaction(ClientDTO clientDTO, CreateTransactionDTO createTransactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setValue(createTransactionDTO.getValue().intValue());
        transaction.setDescription(createTransactionDTO.getDescription());
        transaction.setType(createTransactionDTO.getType());
        transaction.setDateTime(Instant.now());
        transaction.setClientId(clientDTO.getId());
        return transactionRepository.save(transaction)
                .flatMap(t -> saveClient(clientDTO));
    }

    private Mono<ClientDTO> saveClient(ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }

    private CreateTransactionResponseDTO createReturn(ClientDTO clientDTO) {
        CreateTransactionResponseDTO createTransactionResponseDTO = new CreateTransactionResponseDTO();
        createTransactionResponseDTO.setBalance(clientDTO.getBalance());
        createTransactionResponseDTO.setLimit(clientDTO.getLimit());
        return createTransactionResponseDTO;
    }

    ClientDTO validateNewBalance(ClientDTO clientDTO) {
        if (clientDTO.getLimit() < Math.abs(clientDTO.getBalance()) && Math.signum(clientDTO.getBalance()) == -1) {
            throw new NegativeBalanceException();
        }
        return clientDTO;
    }

    private ClientDTO getNewBalance(ClientDTO clientDTO, CreateTransactionDTO createTransactionDTO) {
        int oldBalance = clientDTO.getBalance();
        int transactionValue = createTransactionDTO.getValue().intValue();

        int newBalance;
        if (createTransactionDTO.getType().equalsIgnoreCase("d")) {
            newBalance = oldBalance - transactionValue;
        } else if (createTransactionDTO.getType().equalsIgnoreCase("c")) {
            newBalance = oldBalance + transactionValue;
        } else {
            throw new InvalidTransactionException();
        }

        clientDTO.setBalance(newBalance);
        return clientDTO;
    }

}
