package com.nhomA.mockproject.dto;

import jakarta.persistence.Column;

public class OrderPaymentVnPayDTO {
    private String address;
    private String name;
    private String phone;
    private String vnpAmount;
    private String vnpBankCode;
    private String vnpTransactionNo;
    private String vnpOrderInfo;
    private String vnpSecureHash;
    private String vnpPayDate;
    private String vnpTxnRef;

    public OrderPaymentVnPayDTO() {
    }

    public OrderPaymentVnPayDTO(String address, String name, String phone, String vnpAmount, String vnpBankCode, String vnpTransactionNo, String vnpOrderInfo, String vnpSecureHash, String vnpPayDate, String vnpTxnRef) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.vnpAmount = vnpAmount;
        this.vnpBankCode = vnpBankCode;
        this.vnpTransactionNo = vnpTransactionNo;
        this.vnpOrderInfo = vnpOrderInfo;
        this.vnpSecureHash = vnpSecureHash;
        this.vnpPayDate = vnpPayDate;
        this.vnpTxnRef = vnpTxnRef;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getVnpAmount() {
        return vnpAmount;
    }

    public void setVnpAmount(String vnpAmount) {
        this.vnpAmount = vnpAmount;
    }

    public String getVnpBankCode() {
        return vnpBankCode;
    }

    public void setVnpBankCode(String vnpBankCode) {
        this.vnpBankCode = vnpBankCode;
    }

    public String getVnpTransactionNo() {
        return vnpTransactionNo;
    }

    public void setVnpTransactionNo(String vnpTransactionNo) {
        this.vnpTransactionNo = vnpTransactionNo;
    }

    public String getVnpOrderInfo() {
        return vnpOrderInfo;
    }

    public void setVnpOrderInfo(String vnpOrderInfo) {
        this.vnpOrderInfo = vnpOrderInfo;
    }

    public String getVnpSecureHash() {
        return vnpSecureHash;
    }

    public void setVnpSecureHash(String vnpSecureHash) {
        this.vnpSecureHash = vnpSecureHash;
    }

    public String getVnpPayDate() {
        return vnpPayDate;
    }

    public void setVnpPayDate(String vnpPayDate) {
        this.vnpPayDate = vnpPayDate;
    }

    public String getVnpTxnRef() {
        return vnpTxnRef;
    }

    public void setVnpTxnRef(String vnpTxnRef) {
        this.vnpTxnRef = vnpTxnRef;
    }
}
