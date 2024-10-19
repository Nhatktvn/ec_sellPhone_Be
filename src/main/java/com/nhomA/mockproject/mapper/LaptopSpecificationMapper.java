package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.LaptopSpecificationDTO;
import com.nhomA.mockproject.dto.PhoneSpecificationDTO;
import com.nhomA.mockproject.entity.LaptopSpecification;
import com.nhomA.mockproject.entity.PhoneSpecification;

public interface LaptopSpecificationMapper {
    LaptopSpecification toEntity (LaptopSpecificationDTO laptopSpecificationDTO);
    LaptopSpecificationDTO toDTO (LaptopSpecification laptopSpecification);
}
