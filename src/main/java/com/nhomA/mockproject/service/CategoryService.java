package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO getCategoryById (Long id);
    List<CategoryDTO> getCategoryPagingAndSort(int pageNo, int pageSize, String sortBy, String sortDir);
   CategoryDTO createCategory (CategoryDTO categoryDTO, String username);
   CategoryDTO updateCategoryById (String username, Long id, CategoryDTO categoryDTO);
   Boolean deleteCategoryById(Long id);
    List<CategoryDTO> getCategories();
}
