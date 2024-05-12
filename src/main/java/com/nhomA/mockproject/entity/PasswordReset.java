package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Password_reset")
public class PasswordReset {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_reset")
    private Long codeReset;
    @OneToOne(mappedBy = "passwordReset")
    private User user;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;


    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeReset() {
        return codeReset;
    }

    public void setCodeReset(Long codeReset) {
        this.codeReset = codeReset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
