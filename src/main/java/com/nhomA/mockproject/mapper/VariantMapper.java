package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.VariantDTO;
import com.nhomA.mockproject.entity.Variant;

import java.util.List;

public interface VariantMapper {
    Variant toEntity (VariantDTO variantDTO);
    List<Variant> toListEntity (List<VariantDTO> variantDTOList);

    VariantDTO toDTO (Variant variant);
    List<VariantDTO> toListDTO (List<Variant> variants);
}
