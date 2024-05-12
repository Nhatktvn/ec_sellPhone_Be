package com.nhomA.mockproject.service;

import jakarta.validation.constraints.Email;

public interface LoginService {
    boolean checkActiveAccount (String username);
}
