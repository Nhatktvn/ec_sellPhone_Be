package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.CustomUserDetail;
import com.nhomA.mockproject.entity.Role;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User userEntity = user.get();
        return new CustomUserDetail(userEntity.getUsername(), userEntity.getPassword(), (Set<Role>) userEntity.getRoles());
    }
}
