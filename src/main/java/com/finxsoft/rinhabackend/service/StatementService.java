package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.dto.BalanceDTO;
import com.finxsoft.rinhabackend.dto.ClientDTO;
import com.finxsoft.rinhabackend.dto.StatementResponseDTO;
import com.finxsoft.rinhabackend.mapper.StatementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

/**
 * @author finx
 */
@Service
public class StatementService {

    private static final Logger log = LoggerFactory.getLogger(StatementService.class);

    private final ClientService clientService;

    private final TransactionService transactionService;

    private final StatementMapper statementMapper;

    public StatementService(ClientService clientService, TransactionService transactionService, StatementMapper statementMapper) {
        this.clientService = clientService;
        this.transactionService = transactionService;
        this.statementMapper = statementMapper;
    }

    @Transactional(readOnly = true)
    public Mono<StatementResponseDTO> getStatement(Long id) {
        return clientService.findById(id).flatMap(this::createStatementResponseDTO);
    }

    private Mono<StatementResponseDTO> createStatementResponseDTO(ClientDTO clientDTO) {
        log.debug("Creating a statement for the client with id {}", clientDTO.getId());
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setDate(ZonedDateTime.now());
        balanceDTO.setLimit(clientDTO.getLimit());
        balanceDTO.setTotal(clientDTO.getBalance());

        return transactionService
                .findLastTransactions(clientDTO)
                .collectList()
                .map(transactions -> statementMapper.toDTO(balanceDTO, transactions));
    }

}
