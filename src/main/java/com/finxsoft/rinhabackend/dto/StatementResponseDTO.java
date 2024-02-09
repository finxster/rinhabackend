package com.finxsoft.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author finx
 */
public class StatementResponseDTO {

    @JsonProperty("saldo")
    private BalanceDTO balanceDTO;

    @JsonProperty("ultimas_transacoes")
    private List<TransactionDTO> transactions;

    public BalanceDTO getBalanceDTO() {
        return balanceDTO;
    }

    public void setBalanceDTO(BalanceDTO balanceDTO) {
        this.balanceDTO = balanceDTO;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

}
