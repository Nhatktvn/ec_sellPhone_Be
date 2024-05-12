package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.ListProductPageDTO;
import com.nhomA.mockproject.dto.ProductRequestDTO;
import com.nhomA.mockproject.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO getProductById (Long id);
    List<ProductResponseDTO> getAllProduct();
    ListProductPageDTO getProductPage(int pageNo, int pageSize, String sortBy, String sortDir, Long idCategory);
    ProductResponseDTO createProduct (String username, ProductRequestDTO productRequestDTO);
    List<ProductResponseDTO> createProductByFile (String username, List<ProductRequestDTO> productRequestDTOs);
    ProductResponseDTO updateProductById (String uysername,Long id, ProductRequestDTO productRequestDTO);
    Boolean deleteProductById(Long id);
    List<ProductResponseDTO> getProductsByCategory (String categoryName);
    List<ProductResponseDTO> searchProduct (String searchName);

    ProductResponseDTO getProductByNameExact (String name);

    List<String> getNameSearch (String keyName);
}