package com.nhomA.mockproject.dto;

public class VariantRequestDTO {
    private String color;
    private String storage;
    private Long productId;

    public VariantRequestDTO(String color, String storage, Long productId) {
        this.color = color;
        this.storage = storage;
        this.productId = productId;
    }

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
