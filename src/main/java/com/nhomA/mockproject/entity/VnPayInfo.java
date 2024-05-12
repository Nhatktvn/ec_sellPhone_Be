package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vnPay_info")
public class VnPayInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "vnp_Amount")
    private String vnpAmount;
    @Column(name = "vnp_BankCode")
    private String vnpBankCode;
    @Column(name = "vnp_TransactionNo")
    private String vnpTransactionNo;
    @Column(name = "vnp_OrderInfo")
    private String vnpOrderInfo;
    @Column(name = "vnp_SecureHash", length = -1, unique = true)
    private String vnpSecureHash;
    @Column(name = "vnp_PayDate")
    private String vnpPayDate;
    @Column(name = "vnp_TxnRef")
    private String vnpTxnRef;

    @OneToOne(mappedBy = "vnPayInfo", cascade = CascadeType.ALL)
    private Order order;

    public VnPayInfo() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public VnPayInfo( String vnpAmount, String vnpBankCode, String vnpTransactionNo, String vnpOrderInfo, String vnpSecureHash, String vnpPayDate, String vnpTxnRef) {
        this.vnpAmount = vnpAmount;
        this.vnpBankCode = vnpBankCode;
        this.vnpTransactionNo = vnpTransactionNo;
        this.vnpOrderInfo = vnpOrderInfo;
        this.vnpSecureHash = vnpSecureHash;
        this.vnpPayDate = vnpPayDate;
        this.vnpTxnRef = vnpTxnRef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
