package com.nhomA.mockproject.dto;

public class RoleUserDTO {
    private Long idUser;
    private Long idRole;

    public RoleUserDTO() {
    }

    public RoleUserDTO(Long idUser, Long idRole) {
        this.idUser = idUser;
        this.idRole = idRole;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
}
