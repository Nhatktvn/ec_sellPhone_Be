package com.nhomA.mockproject.dto;

import java.time.LocalDate;

public class OrderRequestDTO {
    private String address;
    private String name;
    private String phone;
    private String noteOrder;

    public OrderRequestDTO() {
    }

    @Override
    public String toString() {
        return "{" +
                "addressId=" + address +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public OrderRequestDTO(String address, String name, String phone, String noteOrder) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.noteOrder = noteOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNoteOrder() {
        return noteOrder;
    }

    public void setNoteOrder(String noteOrder) {
        this.noteOrder = noteOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
