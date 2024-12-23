package com.nhomA.mockproject.dto;

import jakarta.validation.constraints.Email;

public class UpdateUserRequestDTO {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    @Email(message = "Invalid email format")
    private String email;

    public UpdateUserRequestDTO(String username, String password, String fullName, String phone, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
