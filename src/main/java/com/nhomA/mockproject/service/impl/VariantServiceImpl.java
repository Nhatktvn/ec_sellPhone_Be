package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.VariantDTO;
import com.nhomA.mockproject.dto.VariantRequestDTO;
import com.nhomA.mockproject.entity.Product;
import com.nhomA.mockproject.entity.Variant;
import com.nhomA.mockproject.exception.ProductNotFoundException;
import com.nhomA.mockproject.exception.VariantProductNotFoundException;
import com.nhomA.mockproject.mapper.VariantMapper;
import com.nhomA.mockproject.repository.ProductRepository;
import com.nhomA.mockproject.repository.VariantRepository;
import com.nhomA.mockproject.service.VariantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService {
    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;
    private final ProductRepository productRepository;
    public VariantServiceImpl(VariantRepository variantRepository, VariantMapper variantMapper, ProductRepository productRepository) {
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
        this.productRepository = productRepository;
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

    @Override
    public boolean checkAvailable(String productName) {
        Optional<Product> productExisted = productRepository.findByNameExact(productName);
        if (productExisted.isEmpty()){
            throw new ProductNotFoundException("product not found");
        }
        Long productId = productExisted.get().getId();
        List<Variant> variants = variantRepository.findByProductId(productId);
        for(Variant v: variants){
            if(v.getAvailable() > 0){
                return true;
            }
        }
        return false;
    }
}
