package com.finxsoft.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author finx
 */
public class CreateTransactionDTO {

    @JsonProperty("valor")
    private int value;

    @JsonProperty("tipo")
    private String type;

    @JsonProperty("descricao")
    private String description;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
