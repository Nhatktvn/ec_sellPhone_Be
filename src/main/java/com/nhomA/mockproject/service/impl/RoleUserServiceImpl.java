package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.RoleUserDTO;
import com.nhomA.mockproject.entity.Role;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.repository.RoleRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.RoleUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleUserServiceImpl implements RoleUserService{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleUserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public boolean setRole(RoleUserDTO roleUserDTO) throws RoleNotFoundException {
        Optional<Role> emptyRole = roleRepository.findById(roleUserDTO.getIdRole());
        if(emptyRole.isEmpty()){
            throw new RoleNotFoundException("Role not found!");
        }
        Optional<User> emptyUser = userRepository.findById(roleUserDTO.getIdUser());
        if(emptyUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        Set<Role> roles = emptyUser.get().getRoles();
        roles.clear();
        roles.add(emptyRole.get());
        return true;
    }
}
