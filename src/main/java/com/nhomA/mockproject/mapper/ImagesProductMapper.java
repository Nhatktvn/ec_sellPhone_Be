package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.ImageProductDTO;
import com.nhomA.mockproject.entity.ImagesProduct;

import java.util.List;

    public interface ImagesProductMapper {
    ImageProductDTO toDTO (ImagesProduct imagesProduct);
    List<ImageProductDTO> toDTOs (List<ImagesProduct> imagesProducts);
}
