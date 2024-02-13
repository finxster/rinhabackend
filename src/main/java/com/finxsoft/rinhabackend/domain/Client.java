package com.finxsoft.rinhabackend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author finx
 */
@Table("client")
public class Client {

    @Id
    private Long id;

    private int balance;

    @Column("balance_limit")
    private int limit;

    public Client() {
    }

    public Client(Long id, int balance, int limit) {
        this.id = id;
        this.balance = balance;
        this.limit = limit;
    }

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
