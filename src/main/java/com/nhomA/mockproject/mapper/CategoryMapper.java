package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.BrandDTO;
import com.nhomA.mockproject.dto.CategoryDTO;
import com.nhomA.mockproject.entity.Brand;
import com.nhomA.mockproject.entity.Category;

import java.util.List;

public interface CategoryMapper {
    Category toEntity (CategoryDTO categoryDTO);
    CategoryDTO toDTO (Category category);
    List<CategoryDTO> toDTOs (List<Category> categories);
}
