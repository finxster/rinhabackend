package com.finxsoft.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.util.ReflectionUtils;

/**
 * @author finx
 */
public class CreateTransactionDTO {

    @JsonProperty("valor")
    private int value;

    @JsonProperty(value = "tipo", required = true)
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }

}
