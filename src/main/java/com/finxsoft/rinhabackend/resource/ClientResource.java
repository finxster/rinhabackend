package com.finxsoft.rinhabackend.resource;

import com.finxsoft.rinhabackend.dto.CreateTransactionDTO;
import com.finxsoft.rinhabackend.dto.CreateTransactionResponseDTO;
import com.finxsoft.rinhabackend.dto.StatementResponseDTO;
import com.finxsoft.rinhabackend.service.StatementService;
import com.finxsoft.rinhabackend.service.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author finx
 */
@RestController
@RequestMapping("/clientes")
public class ClientResource {

    private static final Logger log = LoggerFactory.getLogger(ClientResource.class);

    private final TransactionService transactionService;

    private final StatementService statementService;

    public ClientResource(TransactionService transactionService, StatementService statementService) {
        this.transactionService = transactionService;
        this.statementService = statementService;
    }

    @PostMapping("/{id}/transacoes")
    public Mono<ResponseEntity<CreateTransactionResponseDTO>> newTransaction(@PathVariable("id") Long id,
                                                                             @RequestBody @Valid CreateTransactionDTO createTransactionDTO) {
        log.info("REST request to create a new transaction for client {}. Transaction details {}", id, createTransactionDTO);
        return transactionService.newTransaction(id, createTransactionDTO).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}/extrato")
    public Mono<ResponseEntity<StatementResponseDTO>> transactions(@PathVariable("id") Long id) {
        log.info("REST request to retrieve balance and transactions for client {}", id);
        return statementService.getStatement(id).map(ResponseEntity::ok);
    }

}
