package com.nhomA.mockproject.dto;

public class ForgotPasswordRequestDTO {
    private String codeReset;
    private String email;
    private String password;

    public ForgotPasswordRequestDTO(String codeReset, String email, String password) {
        this.codeReset = codeReset;
        this.email = email;
        this.password = password;
    }

    public String getCodeReset() {
        return codeReset;
    }

    public void setCodeReset(String codeReset) {
        this.codeReset = codeReset;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}