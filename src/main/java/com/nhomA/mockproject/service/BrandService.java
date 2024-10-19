package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.BrandDTO;

import java.util.List;

public interface BrandService {
    BrandDTO getBrandById(Long id);
    List<BrandDTO> getBrandPagingAndSort(int pageNo, int pageSize, String sortBy, String sortDir);
   BrandDTO createBrand (BrandDTO brandDTO, String username);
   BrandDTO updateBrandById(String username, Long id, BrandDTO brandDTO);
   Boolean deleteBrandById(Long id);
    List<BrandDTO> getBrands();
    List<BrandDTO> getBrandsByCategoryId(Long categoryId);
    List<BrandDTO> getBrandsByCategoryName(String categoryName);

}
