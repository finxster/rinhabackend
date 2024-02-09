package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.domain.Client;
import com.finxsoft.rinhabackend.exception.NegativeBalanceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luis Alves
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void validateNewBalance() {
        Client client = new Client();
        client.setBalance(1000);
        client.setLimit(1000);

        Client clientValidated = transactionService.validateNewBalance(client);

        assertEquals(1000, clientValidated.getBalance());
        assertEquals(1000, clientValidated.getLimit());
    }


    @Test
    void validateNewBalance_1() {
        Client client = new Client();
        client.setBalance(-1000);
        client.setLimit(1000);

        Client clientValidated = transactionService.validateNewBalance(client);

        assertEquals(-1000, clientValidated.getBalance());
        assertEquals(1000, clientValidated.getLimit());
    }

    @Test
    void validateNewBalance_2() {
        Client client = new Client();
        client.setBalance(-2000);
        client.setLimit(1000);

        assertThrows(NegativeBalanceException.class,
                () -> transactionService.validateNewBalance(client));
    }

    @Test
    void validateNewBalance_3() {
        Client client = new Client();
        client.setBalance(0);
        client.setLimit(1000);

        Client clientValidated = transactionService.validateNewBalance(client);

        assertEquals(0, clientValidated.getBalance());
        assertEquals(1000, clientValidated.getLimit());
    }

}
