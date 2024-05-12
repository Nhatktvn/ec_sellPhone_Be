package com.nhomA.mockproject.dto;

public class ActiveAccountRequestDTO {
    private String email;
    private String codeActive;

    public ActiveAccountRequestDTO(String email, String codeActive) {
        this.email = email;
        this.codeActive = codeActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeActive() {
        return codeActive;
    }

    public void setCodeActive(String codeActive) {
        this.codeActive = codeActive;
    }
}
