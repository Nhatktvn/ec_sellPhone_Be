package com.nhomA.mockproject.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class UpdateQuantityCartItemDTO {
    private Long idCartItem;
    private int quantity;

    public Long getIdCartItem() {
        return idCartItem;
    }

    public void setIdCartItem(Long idCartItem) {
        this.idCartItem = idCartItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
