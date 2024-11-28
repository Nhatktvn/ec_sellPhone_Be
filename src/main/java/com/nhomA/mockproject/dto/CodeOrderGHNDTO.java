package com.nhomA.mockproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeOrderGHNDTO {
    @JsonProperty("order_code")
    private String codeOrder;

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }
}
