package com.nhomA.mockproject.dto;

import java.util.List;

public class ListProductPageDTO {
    private List<ProductResponseDTO> products;
    private int pageSize;

    public ListProductPageDTO(List<ProductResponseDTO> products, int pageSize) {
        this.products = products;
        this.pageSize = pageSize;
    }

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
