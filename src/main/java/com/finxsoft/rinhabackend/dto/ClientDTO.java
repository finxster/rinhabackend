package com.finxsoft.rinhabackend.dto;

/**
 * @author finx
 */
public class ClientDTO {

    private Long id;

    private int balance;

    private int limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
