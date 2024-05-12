package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.ActiveAccountRequestDTO;

public interface ActiveAccountService {
    boolean sendCodeActiveToEmail (String username);
    boolean activeAccount (ActiveAccountRequestDTO activeAccountRequestDTO);
}
