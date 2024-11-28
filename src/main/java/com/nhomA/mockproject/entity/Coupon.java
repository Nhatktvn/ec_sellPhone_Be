package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_coupon")
    private String codeCoupon;
    @Column(name = "type_coupon")
    private String typeCoupon;
    @Column(name = "coupon_value")
    private double couponValue;
    @Column(name = "minimum_amount")
    private double minimumAmount;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<CouponUser> couponUsers;

    public Coupon() {
    }

    public Coupon(Long id, String codeCoupon, String typeCoupon, double couponValue, double minimumAmount, int quantity, LocalDateTime startTime, LocalDateTime endTime, List<CouponUser> couponUsers) {
        this.id = id;
        this.codeCoupon = codeCoupon;
        this.typeCoupon = typeCoupon;
        this.couponValue = couponValue;
        this.minimumAmount = minimumAmount;
        this.quantity = quantity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.couponUsers = couponUsers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<CouponUser> getCouponUsers() {
        return couponUsers;
    }

    public void setCouponUsers(List<CouponUser> couponUsers) {
        this.couponUsers = couponUsers;
    }
}
