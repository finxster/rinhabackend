package com.finxsoft.rinhabackend.mapper;

import com.finxsoft.rinhabackend.dto.BalanceDTO;
import com.finxsoft.rinhabackend.dto.StatementResponseDTO;
import com.finxsoft.rinhabackend.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author finx
 */
@Component
public class StatementMapper {

    public StatementResponseDTO toDTO(BalanceDTO balanceDTO, List<TransactionDTO> transactions) {
        StatementResponseDTO statementResponseDTO = new StatementResponseDTO();
        statementResponseDTO.setBalanceDTO(balanceDTO);
        statementResponseDTO.setTransactions(transactions);
        return statementResponseDTO;
    }

}
