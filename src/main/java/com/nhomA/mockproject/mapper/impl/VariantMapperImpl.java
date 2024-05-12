package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.VariantDTO;
import com.nhomA.mockproject.entity.Variant;
import com.nhomA.mockproject.mapper.VariantMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VariantMapperImpl implements VariantMapper {
    @Override
    public Variant toEntity(VariantDTO variantDTO) {
        Variant variant = new Variant();
        variant.setOriginalPrice(variantDTO.getOriginalPrice());
        variant.setSellPrice(variantDTO.getSellPrice());
        variant.setColor(variantDTO.getColor());
        variant.setStorageCapacity(variantDTO.getStorageCapacity());
        variant.setAvailable(variantDTO.getAvailable());
        return variant;
    }

    @Override
    public List<Variant> toListEntity(List<VariantDTO> variantDTOList) {
        List<Variant> variants = new ArrayList<>();
        for(VariantDTO v : variantDTOList){
            variants.add(this.toEntity(v));
        }
        return variants;
    }

    @Override
    public VariantDTO toDTO(Variant variant) {
        VariantDTO variantDTO = new VariantDTO();
        variantDTO.setColor(variant.getColor());
        variantDTO.setOriginalPrice(variant.getOriginalPrice());
        variantDTO.setSellPrice(variant.getSellPrice());
        variantDTO.setStorageCapacity(variant.getStorageCapacity());
        variantDTO.setAvailable(variant.getAvailable());
        return variantDTO;
    }

    @Override
    public List<VariantDTO> toListDTO(List<Variant> variants) {
        List<VariantDTO> variantDTOList = new ArrayList<>();
        for(Variant v : variants){
            variantDTOList.add(this.toDTO(v));
        }
        return variantDTOList;
    }
}
