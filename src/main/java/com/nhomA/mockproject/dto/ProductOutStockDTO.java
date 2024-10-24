package com.nhomA.mockproject.dto;

public class ProductOutStockDTO {
    private Long idLineItem;
    private int available;

    public ProductOutStockDTO(Long idLineItem, int available) {
        this.idLineItem = idLineItem;
        this.available = available;
    }

    public ProductOutStockDTO() {
    }

    public Long getIdLineItem() {
        return idLineItem;
    }

    public void setIdLineItem(Long idLineItem) {
        this.idLineItem = idLineItem;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
