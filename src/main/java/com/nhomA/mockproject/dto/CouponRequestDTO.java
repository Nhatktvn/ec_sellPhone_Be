package com.nhomA.mockproject.dto;

import java.time.LocalDateTime;

public class CouponRequestDTO {
    private String codeCoupon;
    private String typeCoupon;
    private double couponValue;
    private double minimumAmount;
    private int quantity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public CouponRequestDTO() {
    }

    public CouponRequestDTO(String codeCoupon, String typeCoupon, double couponValue, double minimumAmount, int quantity, LocalDateTime startTime, LocalDateTime endTime) {
        this.codeCoupon = codeCoupon;
        this.typeCoupon = typeCoupon;
        this.couponValue = couponValue;
        this.minimumAmount = minimumAmount;
        this.quantity = quantity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCodeCoupon() {
        return codeCoupon;
    }

    public void setCodeCoupon(String codeCoupon) {
        this.codeCoupon = codeCoupon;
    }

    public String getTypeCoupon() {
        return typeCoupon;
    }

    public void setTypeCoupon(String typeCoupon) {
        this.typeCoupon = typeCoupon;
    }

    public double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(double couponValue) {
        this.couponValue = couponValue;
    }

    public double getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(double minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
