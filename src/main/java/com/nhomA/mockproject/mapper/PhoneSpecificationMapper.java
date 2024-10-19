package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.PhoneSpecificationDTO;
import com.nhomA.mockproject.entity.PhoneSpecification;

public interface PhoneSpecificationMapper {
    PhoneSpecification toEntity (PhoneSpecificationDTO phoneSpecificationDTO);
    PhoneSpecificationDTO toDTO (PhoneSpecification phoneSpecification);
}
