package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.VariantDTO;
import com.nhomA.mockproject.dto.VariantRequestDTO;

public interface VariantService {
    VariantDTO getAvailable (VariantRequestDTO variantRequestDTO);
    boolean checkAvailable (String productName);
}
