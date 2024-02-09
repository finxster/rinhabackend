package com.finxsoft.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finxsoft.rinhabackend.domain.Client;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * @author finx
 */
public class TransactionDTO {

    @JsonProperty("valor")
    private Integer value;
    @JsonProperty("tipo")
    private String type;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("realizada_em")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
    private ZonedDateTime date;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

}
