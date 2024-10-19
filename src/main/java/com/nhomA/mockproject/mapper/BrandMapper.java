package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.BrandDTO;
import com.nhomA.mockproject.entity.Brand;

import java.util.List;

public interface BrandMapper {
    Brand toEntity (BrandDTO brandDTO);
    BrandDTO toDTO (Brand brand);
    List<BrandDTO> toDTOs (List<Brand> brands);
}
