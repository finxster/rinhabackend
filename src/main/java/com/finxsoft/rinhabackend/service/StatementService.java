package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.domain.Client;
import com.finxsoft.rinhabackend.dto.BalanceDTO;
import com.finxsoft.rinhabackend.dto.StatementResponseDTO;
import com.finxsoft.rinhabackend.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author finx
 */
@Service
public class StatementService {

    private static final Logger log = LoggerFactory.getLogger(StatementService.class);

    private final ClientService clientService;

    private final TransactionService transactionService;

    public StatementService(ClientService clientService, TransactionService transactionService) {
        this.clientService = clientService;
        this.transactionService = transactionService;
    }

    @Transactional(readOnly = true)
    public Mono<StatementResponseDTO> getStatement(Long id) {
        return clientService.findById(id).map(this::createStatementResponseDTO);
    }

    private StatementResponseDTO createStatementResponseDTO(Client client) {
        log.info("Creating a statement for the client with id {}", client.getId());
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setDate(ZonedDateTime.now());
        balanceDTO.setLimit(client.getLimit());
        balanceDTO.setTotal(client.getBalance());

        Flux<TransactionDTO> transactions = transactionService.findLastTransactions(client);

        StatementResponseDTO statementResponseDTO = new StatementResponseDTO();
        statementResponseDTO.setBalanceDTO(balanceDTO);
        statementResponseDTO.setTransactions(transactions.collectList().block());
        return statementResponseDTO;
    }

}
