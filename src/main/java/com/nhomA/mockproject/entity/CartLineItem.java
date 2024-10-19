package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "cartLine_item")
public class CartLineItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "original_price", nullable = false)
    private double originalPrice;
    @Column(name = "sell_price", nullable = false)
    private double sellPrice;
    @Column(name = "added_date")
    private ZonedDateTime addedDate;
    @Column(name = "color")
    private String color;
    @Column(name = "storage")
    private String storage;
    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public ZonedDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(ZonedDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
