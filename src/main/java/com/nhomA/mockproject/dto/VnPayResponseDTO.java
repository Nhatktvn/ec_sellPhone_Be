package com.nhomA.mockproject.dto;

public class VnPayResponseDTO {
    private String vnpAmount;
    private String vnpBankCode;
    private String vnpTransactionNo;
    private String vnpOrderInfo;
    private String vnpSecureHash;
    private String vnpPayDate;
    private String vnpTxnRef;

    public VnPayResponseDTO() {
    }

    public VnPayResponseDTO(String vnpAmount, String vnpBankCode, String vnpTransactionNo, String vnpOrderInfo, String vnpSecureHash, String vnpPayDate, String vnpTxnRef) {
        this.vnpAmount = vnpAmount;
        this.vnpBankCode = vnpBankCode;
        this.vnpTransactionNo = vnpTransactionNo;
        this.vnpOrderInfo = vnpOrderInfo;
        this.vnpSecureHash = vnpSecureHash;
        this.vnpPayDate = vnpPayDate;
        this.vnpTxnRef = vnpTxnRef;
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
