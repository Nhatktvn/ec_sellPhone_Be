package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.SpecificationDTO;
import com.nhomA.mockproject.entity.Specification;

public interface SpecificationMapper {
    Specification toEntity (SpecificationDTO specificationDTO);
    SpecificationDTO toDTO (Specification specification);
}
