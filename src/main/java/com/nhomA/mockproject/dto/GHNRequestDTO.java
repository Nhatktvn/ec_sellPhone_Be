package com.nhomA.mockproject.dto;

import java.util.ArrayList;
import java.util.List;

public class GHNRequestDTO {
    private String toName;
    private String toPhone;
    private String toAddress;
    private String toWardCode;
    private int toDistrictId;
    private int serviceId;
    private int serviceTypeId;
    private Long codAmount;
    private List<ItemGHNDTO> items;

    public GHNRequestDTO(String toName, String toPhone, String toAddress, String toWardCode, int toDistrictId, int serviceId, int serviceTypeId, Long codAmount) {
        this.toName = toName;
        this.toPhone = toPhone;
        this.toAddress = toAddress;
        this.toWardCode = toWardCode;
        this.toDistrictId = toDistrictId;
        this.serviceId = serviceId;
        this.serviceTypeId = serviceTypeId;
        this.codAmount = codAmount;
        this.items = new ArrayList<>();
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getToWardCode() {
        return toWardCode;
    }

    public void setToWardCode(String toWardCode) {
        this.toWardCode = toWardCode;
    }

    public int getToDistrictId() {
        return toDistrictId;
    }

    public void setToDistrictId(int toDistrictId) {
        this.toDistrictId = toDistrictId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Long getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(Long codAmount) {
        this.codAmount = codAmount;
    }

    public List<ItemGHNDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemGHNDTO> items) {
        this.items = items;
    }
}