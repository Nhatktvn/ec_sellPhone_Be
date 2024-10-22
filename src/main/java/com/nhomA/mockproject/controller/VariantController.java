package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.VariantRequestDTO;
import com.nhomA.mockproject.service.VariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VariantController {
    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @PostMapping("/variant/get-available")
    public ResponseEntity<?> getAvailable (@RequestBody VariantRequestDTO variantRequestDTO){
        try{
            return new ResponseEntity<> (variantService.getAvailable(variantRequestDTO), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
