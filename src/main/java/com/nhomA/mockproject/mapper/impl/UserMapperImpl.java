package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.UserDTO;
import com.nhomA.mockproject.entity.Identification;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl  implements UserMapper {
    @Override
    public User toEntity(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setEmail(user.getIdentification().getEmail());
        userDTO.setPhone(user.getIdentification().getPhone());
        userDTO.setFullName(user.getIdentification().getFullName());
        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOs(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User u : users){
            userDTOS.add(this.toDTO(u));
        }
        return userDTOS;
    }
}
