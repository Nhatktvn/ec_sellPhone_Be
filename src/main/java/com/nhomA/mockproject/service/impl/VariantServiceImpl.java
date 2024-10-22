package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.VariantDTO;
import com.nhomA.mockproject.dto.VariantRequestDTO;
import com.nhomA.mockproject.entity.Variant;
import com.nhomA.mockproject.exception.VariantProductNotFoundException;
import com.nhomA.mockproject.mapper.VariantMapper;
import com.nhomA.mockproject.repository.VariantRepository;
import com.nhomA.mockproject.service.VariantService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService {
    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;
    public VariantServiceImpl(VariantRepository variantRepository, VariantMapper variantMapper) {
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
    }

    @Override
    public VariantDTO getAvailable(VariantRequestDTO variantRequestDTO) {
        Optional<Variant> variant = variantRepository.findByColorAndStorageCapacityAndProductId(variantRequestDTO.getColor(),variantRequestDTO.getStorage(),variantRequestDTO.getProductId());
        if(variant.isEmpty()){
            throw new VariantProductNotFoundException("Variant not found!");
        }
        VariantDTO variantDTO = variantMapper.toDTO(variant.get());
        return variantDTO;
    }
}
