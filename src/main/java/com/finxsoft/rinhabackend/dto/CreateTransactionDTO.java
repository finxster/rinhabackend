package com.finxsoft.rinhabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * @author finx
 */
public class CreateTransactionDTO {

    @Positive
    @Digits(fraction = 0, integer = 10)
    @JsonProperty("valor")
    private BigDecimal value;

    @NotBlank
    @Pattern(regexp = "^[cd]$")
    @JsonProperty(value = "tipo")
    private String type;

    @NotBlank
    @Length(max = 10)
    @JsonProperty("descricao")
    private String description;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
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
