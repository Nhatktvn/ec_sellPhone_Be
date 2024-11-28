package com.nhomA.mockproject.dto;

import java.time.LocalDate;

public class OrderRequestDTO {
    private String codeOrder;
    private int fee;
    private String provinceAddress;
    private String districtAddress;
    private String wardAddress;
    private String streetAddress;
    private String name;
    private String phone;
    private String noteOrder;
    private Long couponId;


    public OrderRequestDTO() {
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public OrderRequestDTO(String codeOrder, String provinceAddress, String districtAddress, String wardAddress, String streetAddress, String name, String phone, String noteOrder, Long couponId) {
        this.codeOrder = codeOrder;
        this.provinceAddress = provinceAddress;
        this.districtAddress = districtAddress;
        this.wardAddress = wardAddress;
        this.streetAddress = streetAddress;
        this.name = name;
        this.phone = phone;
        this.noteOrder = noteOrder;
        this.couponId = couponId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getProvinceAddress() {
        return provinceAddress;
    }

    public void setProvinceAddress(String provinceAddress) {
        this.provinceAddress = provinceAddress;
    }

    public String getDistrictAddress() {
        return districtAddress;
    }

    public void setDistrictAddress(String districtAddress) {
        this.districtAddress = districtAddress;
    }

    public String getWardAddress() {
        return wardAddress;
    }

    public void setWardAddress(String wardAddress) {
        this.wardAddress = wardAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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
