package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.CategoryDTO;
import com.nhomA.mockproject.entity.Brand;
import com.nhomA.mockproject.entity.Category;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.BrandNotFoundException;
import com.nhomA.mockproject.exception.CategoryNotFoundException;
import com.nhomA.mockproject.mapper.CategoryMapper;
import com.nhomA.mockproject.repository.CategoryRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.CategoryService;
import com.nhomA.mockproject.util.PaginationAndSortingUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userRepository = userRepository;
    }


    @Transactional
    @Override
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new CategoryNotFoundException("Category not found");
        }
        return categoryMapper.toDTO(category.get());
    }

    @Transactional
    @Override
    public List<CategoryDTO> getCategoryPagingAndSort(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable= PaginationAndSortingUtils.getPageable(pageNo,pageSize,sortBy,sortDir);
        Page<Category> categories= categoryRepository.findAll(pageable);
        List<Category> categoriesContent = categories.getContent();
        return categoryMapper.toDTOs(categoriesContent);
    }

    @Transactional
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO, String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        User user = existedUser.get();
        Category category = categoryMapper.toEntity(categoryDTO);
        categoryRepository.save(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO updateCategoryById(String username, Long id, CategoryDTO categoryDTO) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        User userUpdated = existedUser.get();
        Optional<Category> existedCategory = categoryRepository.findById(id);
        if(existedCategory.isEmpty()){
            throw new CategoryNotFoundException("Category not found!") ;
        }
        Category category = existedCategory.get();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        categoryRepository.save(category);
        return categoryDTO;
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categoryMapper.toDTOs(categories);
        return categoryDTOS;
    }
}
