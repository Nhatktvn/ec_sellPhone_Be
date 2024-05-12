package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.ProductRequestDTO;
import com.nhomA.mockproject.dto.ProductResponseDTO;
import com.nhomA.mockproject.entity.Product;

import java.util.List;

public interface ProductMapper {
    Product toEntity (ProductRequestDTO productRequestDTO);
    ProductResponseDTO toResponseDTO (Product product);
    List<ProductResponseDTO> toResponseDTOs (List<Product> products);
}
