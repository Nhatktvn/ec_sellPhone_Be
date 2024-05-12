package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.entity.Role;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    final private UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public String getRoleUser(String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        List<Role> listFromSet = new ArrayList<>(existedUser.get().getRoles());

        return listFromSet.get(0).getName();
    }
}
