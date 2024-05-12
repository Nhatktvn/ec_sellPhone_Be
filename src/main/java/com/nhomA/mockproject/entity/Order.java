package com.nhomA.mockproject.entity;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "delivery_time")
    private ZonedDateTime deliveryTime;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vnPayInfo_id", referencedColumnName = "id")
    private VnPayInfo vnPayInfo;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<CartLineItem> cartLineItems;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_order", referencedColumnName = "id")
    private StatusOrder statusOrder;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public VnPayInfo getVnPayInfo() {
        return vnPayInfo;
    }

    public void setVnPayInfo(VnPayInfo vnPayInfo) {
        this.vnPayInfo = vnPayInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ZonedDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(ZonedDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartLineItem> getCartLineItems() {
        return cartLineItems;
    }

    public void setCartLineItems(List<CartLineItem> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
