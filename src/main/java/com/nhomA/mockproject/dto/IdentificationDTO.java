package com.nhomA.mockproject.dto;

import java.time.LocalDate;

public class IdentificationDTO {
    private String fullName;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String urlAvatar;

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public IdentificationDTO() {
    }

    public IdentificationDTO(String fullName, LocalDate birthDate, String phone, String email, String urlAvatar) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.urlAvatar = urlAvatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
}
