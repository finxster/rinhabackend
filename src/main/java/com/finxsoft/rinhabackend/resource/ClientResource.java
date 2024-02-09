package com.finxsoft.rinhabackend.resource;

import com.finxsoft.rinhabackend.dto.CreateTransactionDTO;
import com.finxsoft.rinhabackend.dto.CreateTransactionResponseDTO;
import com.finxsoft.rinhabackend.dto.StatementResponseDTO;
import com.finxsoft.rinhabackend.service.ClientService;
import com.finxsoft.rinhabackend.service.StatementService;
import com.finxsoft.rinhabackend.service.TransactionService;
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

    private final ClientService clientService;

    private final StatementService statementService;

    public ClientResource(TransactionService transactionService, ClientService clientService, StatementService statementService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
        this.statementService = statementService;
    }

    @PostMapping("/{id}/transacoes")
    public Mono<ResponseEntity<CreateTransactionResponseDTO>> newTransaction(@PathVariable("id") Long id, @RequestBody CreateTransactionDTO createTransactionDTO) {
        return transactionService.newTransaction(id, createTransactionDTO).map(ResponseEntity::ok);
    }

    @GetMapping("/{id}/extrato")
    public Mono<ResponseEntity<StatementResponseDTO>> transactions(@PathVariable("id") Long id) {
        log.info("Call to retrieve balance and transactions for client with id {}", id);
        return statementService.getStatement(id).map(ResponseEntity::ok);
    }

}
