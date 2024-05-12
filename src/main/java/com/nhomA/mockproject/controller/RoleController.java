package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.RoleUserDTO;
import com.nhomA.mockproject.service.RoleUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class RoleController {
    private final RoleUserService roleUserService;

    public RoleController(RoleUserService roleUserService) {
        this.roleUserService = roleUserService;
    }

    @PutMapping("/set-role")
    public ResponseEntity<?> setRoleUser (@RequestParam("idUser") Long idUser, @RequestParam("idRole") Long idRole){
        RoleUserDTO roleUserDTO = new RoleUserDTO(idUser,idRole);
        try {
            return new ResponseEntity<>(roleUserService.setRole(roleUserDTO),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
