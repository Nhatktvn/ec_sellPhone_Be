package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.BrandDTO;
import com.nhomA.mockproject.entity.Brand;
import com.nhomA.mockproject.entity.Category;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.BrandNotFoundException;
import com.nhomA.mockproject.exception.CategoryNotFoundException;
import com.nhomA.mockproject.mapper.BrandMapper;
import com.nhomA.mockproject.repository.BrandRepository;
import com.nhomA.mockproject.repository.CategoryRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.BrandService;
import com.nhomA.mockproject.util.PaginationAndSortingUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private final UserRepository userRepository;
    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public BrandServiceImpl(UserRepository userRepository, BrandMapper brandMapper, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.brandMapper = brandMapper;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    @Override
    public BrandDTO getBrandById(Long id) {
        Optional<Brand> category = brandRepository.findById(id);
        if(category.isEmpty()) {
            throw new BrandNotFoundException("Category not found!") ;
        }
        return brandMapper.toDTO(category.get());
    }

    @Transactional
    @Override
    public List<BrandDTO> getBrands() {
        List<Brand> categories = brandRepository.findAll();
        List<BrandDTO> brandDTOS = brandMapper.toDTOs(categories);
        return brandDTOS;
    }

    @Override
    public List<BrandDTO> getBrandsByCategoryId(Long categoryId) {
        List<Brand> brandsByCategoryId = brandRepository.findByCategoryId(categoryId);
        List<BrandDTO> brandDTOS = brandMapper.toDTOs(brandsByCategoryId);
        return brandDTOS;
    }

    @Override
    public List<BrandDTO> getBrandsByCategoryName(String categoryName) {
        List<Brand> brandsByCategoryId = brandRepository.findByCategoryNameContainingIgnoreCase(categoryName);
        List<BrandDTO> brandDTOS = brandMapper.toDTOs(brandsByCategoryId);
        return brandDTOS;
    }

    @Transactional
    @Override
    public List<BrandDTO> getBrandPagingAndSort(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable= PaginationAndSortingUtils.getPageable(pageNo,pageSize,sortBy,sortDir);
        Page<Brand> categories= brandRepository.findAll(pageable);
        List<Brand> categoriesContent = categories.getContent();
        return brandMapper.toDTOs(categoriesContent);
    }

    @Transactional
    @Override
    public BrandDTO createBrand(BrandDTO brandDTO, String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        User user = existedUser.get();
        Brand brand = brandMapper.toEntity(brandDTO);
        Optional<Category> existedCategory = categoryRepository.findById(brandDTO.getCategoryId());
        if(existedCategory.isEmpty()){
            throw new CategoryNotFoundException("Category not found!") ;
        }
        brand.setUserCreated(user);
        brand.setUserUpdated(user);
        brand.setCreatedDate(ZonedDateTime.now());
        brand.setUpdatedDate(ZonedDateTime.now());
        brand.setCategory(existedCategory.get());
        existedCategory.get().getBrands().add(brand);
        brandRepository.save(brand);
        return brandDTO;
    }
    @Transactional
    @Override
    public BrandDTO updateBrandById(String username, Long id, BrandDTO brandDTO) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        User userUpdated = existedUser.get();
        Optional<Brand> existedCategory = brandRepository.findById(id);
        if(existedCategory.isEmpty()){
            throw new BrandNotFoundException("Category not found!") ;
        }
        Brand brand = existedCategory.get();
        brand.setName(brandDTO.getName());
        brand.setDescription(brandDTO.getDescription());
        if(brandDTO.getUrlImage() != ""){
            brand.setUrlImage(brandDTO.getUrlImage());
        }
        brand.setUserUpdated(userUpdated);
        brand.setUpdatedDate(ZonedDateTime.now());
        brandRepository.save(brand);
    return brandDTO;
    }
    @Transactional
    @Override
    public Boolean deleteBrandById(Long id) {
        brandRepository.deleteById(id);
        return true;
    }
}
