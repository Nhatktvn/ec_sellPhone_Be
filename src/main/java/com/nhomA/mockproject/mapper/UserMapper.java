package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.UserDTO;
import com.nhomA.mockproject.entity.User;

import java.util.List;

public interface UserMapper {
    User toEntity (UserDTO userDTO);
    UserDTO toDTO (User user);
    List<UserDTO> toDTOs (List<User> users);
}
