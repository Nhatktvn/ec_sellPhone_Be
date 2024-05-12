package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.CategoryDTO;
import com.nhomA.mockproject.dto.UserDTO;
import com.nhomA.mockproject.entity.Category;
import com.nhomA.mockproject.entity.User;
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
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(UserRepository userRepository, CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    @Override
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new CategoryNotFoundException("Category not found!") ;
        }
        return categoryMapper.toDTO(category.get());
    }

    @Transactional
    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categoryMapper.toDTOs(categories);
        return categoryDTOS;
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
        category.setUserCreated(user);
        category.setUserUpdated(user);
        category.setCreatedDate(ZonedDateTime.now());
        category.setUpdatedDate(ZonedDateTime.now());
        categoryRepository.save(category);
        return categoryDTO;
    }
    @Transactional
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
        if(categoryDTO.getUrlImage() != ""){
            category.setUrlImage(categoryDTO.getUrlImage());
        }
        category.setUserUpdated(userUpdated);
        category.setUpdatedDate(ZonedDateTime.now());
        categoryRepository.save(category);
    return categoryDTO;
    }
    @Transactional
    @Override
    public Boolean deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
