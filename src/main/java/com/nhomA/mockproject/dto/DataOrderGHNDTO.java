package com.nhomA.mockproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataOrderGHNDTO {
    @JsonProperty("data")
    private CodeOrderGHNDTO data;

    public CodeOrderGHNDTO getData() {
        return data;
    }

    public void setData(CodeOrderGHNDTO data) {
        this.data = data;
    }
}
