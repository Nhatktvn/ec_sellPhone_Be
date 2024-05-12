package com.nhomA.mockproject.dto;

public class VariantDTO {
    private String color;
    private String storageCapacity;
    private double originalPrice;
    private double sellPrice;
    private int available;


    public VariantDTO(String color, String storageCapacity, double originalPrice, double sellPrice, int available) {
        this.color = color;
        this.storageCapacity = storageCapacity;
        this.originalPrice = originalPrice;
        this.sellPrice = sellPrice;
        this.available = available;
    }

    public VariantDTO (){

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
}
