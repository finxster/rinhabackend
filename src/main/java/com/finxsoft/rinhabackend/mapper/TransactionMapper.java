package com.finxsoft.rinhabackend.mapper;

import com.finxsoft.rinhabackend.domain.Transaction;
import com.finxsoft.rinhabackend.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author finx
 */
@Component
public class TransactionMapper {

    public TransactionDTO toDto(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setValue(transaction.getValue());
        transactionDTO.setDate(ZonedDateTime.ofInstant(transaction.getDateTime(), ZoneId.of("America/Sao_Paulo")));
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setType(transaction.getType());
        return transactionDTO;
    }

}
