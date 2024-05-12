package com.nhomA.mockproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "variant")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "color")
    private String color;
    @Column(name = "storage_capacity")
    private String storageCapacity;
    @Column(name = "original_price")
    private double originalPrice;

    @Column(name = "sell_price")
    private double sellPrice;
    @Column(name = "available")
    private int available;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Variant() {
    }

    public Variant(Long id, String color, String storageCapacity, double originalPrice, double sellPrice, int available, Product product) {
        this.id = id;
        this.color = color;
        this.storageCapacity = storageCapacity;
        this.originalPrice = originalPrice;
        this.sellPrice = sellPrice;
        this.available = available;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(String storageCapacity) {
        this.storageCapacity = storageCapacity;
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
