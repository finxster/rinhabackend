package com.finxsoft.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author finx
 */
public class CreateTransactionResponseDTO {

    @JsonProperty("limite")
    private int limit;

    @JsonProperty("saldo")
    private int balance;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
