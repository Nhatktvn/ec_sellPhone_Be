package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.RoleUserDTO;

import javax.management.relation.RoleNotFoundException;

public interface RoleUserService {
    boolean setRole (RoleUserDTO roleUserDTO) throws RoleNotFoundException;
}
