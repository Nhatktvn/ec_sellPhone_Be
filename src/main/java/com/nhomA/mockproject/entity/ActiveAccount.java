package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "active_account")
public class ActiveAccount {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_active", nullable = false)
    private String codeActive;
    @Column(name = "time_expired")
    private LocalDateTime timeExpired;

    @OneToOne(mappedBy = "activeAccount")
    private User user;

    public ActiveAccount() {
        this.timeExpired = LocalDateTime.now().plusMinutes(5);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeActive() {
        return codeActive;
    }

    public void setCodeActive(String codeActive) {
        this.codeActive = codeActive;
    }

    public LocalDateTime getTimeExpired() {
        return timeExpired;
    }

    public void setTimeExpired(LocalDateTime timeExpired) {
        this.timeExpired = timeExpired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
