package com.finxsoft.rinhabackend.config;

import com.finxsoft.rinhabackend.exception.ClientNotFoundException;
import com.finxsoft.rinhabackend.exception.InvalidTransactionException;
import com.finxsoft.rinhabackend.exception.NegativeBalanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @author finx
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler(value = {ClientNotFoundException.class})
    public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException ex) {
        log.error("Error during request.");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {NegativeBalanceException.class})
    public ResponseEntity<Object> handleNegativeBalanceException(NegativeBalanceException ex) {
        log.error("Error during transaction. Inconsistent balance found.");
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(value = {InvalidTransactionException.class})
    public ResponseEntity<Object> handleInvalidTransactionException(InvalidTransactionException ex) {
        log.error("Error during transaction. Invalid type of transaction.");
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(value = {WebExchangeBindException.class})
    public ResponseEntity<String> handleWebExchangeBindException(WebExchangeBindException ex) {
        log.error("Error during request.");
        return ResponseEntity.unprocessableEntity().build();
    }

}
