package com.finxsoft.rinhabackend.service;

import com.finxsoft.rinhabackend.dto.ClientDTO;
import com.finxsoft.rinhabackend.exception.NegativeBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author finx
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    private ClientDTO client;

    @BeforeEach
    void setup() {
        client = new ClientDTO();
        client.setLimit(1000);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, -1000, 0, 1001})
    void validateNewBalance(int balance) {
        client.setBalance(balance);

        ClientDTO clientValidated = transactionService.validateNewBalance(client);

        assertEquals(balance, clientValidated.getBalance());
        assertEquals(1000, clientValidated.getLimit());
    }

    @ParameterizedTest
    @ValueSource(ints = {-2000, -1001})
    void validateNewBalance_negativeScenarios(int balance) {
        client.setBalance(balance);

        assertThrows(NegativeBalanceException.class,
                () -> transactionService.validateNewBalance(client));
    }

}
