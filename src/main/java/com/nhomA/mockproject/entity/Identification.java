package com.nhomA.mockproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "identification")
public class Identification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url_avatar")
    private String urlAvatar;
    @Column(name = "full_name",length = 100)
    private String fullName;
    @Column(name = "birth_date",length = 100)
    private LocalDate birthDate;

    @Email(message = "Invalid email format")
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "phone")
    private String phone;
    @OneToOne(mappedBy = "identification", cascade = CascadeType.ALL)
    private User user;

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
