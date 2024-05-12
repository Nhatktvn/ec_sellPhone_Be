package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.AccountNotActiveException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional

    @Override
    public boolean checkActiveAccount(String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if (existedUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        if (existedUser.get().getStatus() == false){
            throw  new AccountNotActiveException("account has not been activated!");
        }
        return true;
    }
}
