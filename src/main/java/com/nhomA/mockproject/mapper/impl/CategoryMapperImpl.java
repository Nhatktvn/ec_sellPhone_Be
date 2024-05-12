package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.CategoryDTO;
import com.nhomA.mockproject.entity.Category;
import com.nhomA.mockproject.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setUrlImage(categoryDTO.getUrlImage());
        return category;
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setUrlImage(category.getUrlImage());
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> toDTOs(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category c: categories){
            categoryDTOS.add(this.toDTO(c));
        }
        return categoryDTOS;
    }
}
