package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.ImageProductDTO;
import com.nhomA.mockproject.entity.ImagesProduct;
import com.nhomA.mockproject.mapper.ImagesProductMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImagesProductMapperImpl implements ImagesProductMapper {
    @Override
    public ImageProductDTO toDTO(ImagesProduct imagesProduct) {
        ImageProductDTO imageProductDTO = new ImageProductDTO(imagesProduct.getId(),imagesProduct.getUrlImage());
        return imageProductDTO;
    }

    @Override
    public List<ImageProductDTO> toDTOs(List<ImagesProduct> imagesProducts) {
        List<ImageProductDTO> imageProductDTOs = new ArrayList<>();
        for(ImagesProduct image : imagesProducts){
            ImageProductDTO imageProductDTO = new ImageProductDTO(image.getId(), image.getUrlImage());
            imageProductDTOs.add(imageProductDTO);
        }
        return imageProductDTOs;
    }
}
