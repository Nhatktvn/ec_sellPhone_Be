package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.BrandDTO;
import com.nhomA.mockproject.entity.Brand;
import com.nhomA.mockproject.mapper.BrandMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandMapperImpl implements BrandMapper {
    @Override
    public Brand toEntity(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setDescription(brandDTO.getDescription());
        brand.setUrlImage(brandDTO.getUrlImage());
        return brand;
    }

    @Override
    public BrandDTO toDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setDescription(brand.getDescription());
        brandDTO.setUrlImage(brand.getUrlImage());
        return brandDTO;
    }

    @Override
    public List<BrandDTO> toDTOs(List<Brand> categories) {
        List<BrandDTO> brandDTOS = new ArrayList<>();
        for(Brand c: categories){
            brandDTOS.add(this.toDTO(c));
        }
        return brandDTOS;
    }
}
