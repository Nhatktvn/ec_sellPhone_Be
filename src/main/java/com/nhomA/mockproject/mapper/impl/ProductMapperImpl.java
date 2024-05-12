package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.*;
import com.nhomA.mockproject.entity.Product;
import com.nhomA.mockproject.entity.Reviews;
import com.nhomA.mockproject.mapper.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    private final UserMapper userMapper;
    private final VariantMapper variantMapper;
    private final ImagesProductMapper imagesProductMapper;
    private final SpecificationMapper specificationMapper;
    public ProductMapperImpl(UserMapper userMapper, VariantMapper variantMapper, ImagesProductMapper imagesProductMapper, SpecificationMapper specificationMapper, ReviewMapper reviewMapper) {
        this.userMapper = userMapper;
        this.variantMapper = variantMapper;
        this.imagesProductMapper = imagesProductMapper;
        this.specificationMapper = specificationMapper;
        this.reviewMapper = reviewMapper;
    }
    private  final ReviewMapper reviewMapper;

    @Override
    public Product toEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setImageThumb(productRequestDTO.getImageThumb());
        product.setDescription(productRequestDTO.getDescription());
        return product;
    }

    @Override
    public ProductResponseDTO toResponseDTO(Product product) {
       ProductResponseDTO productResponseDTO = new ProductResponseDTO();
       productResponseDTO.setId(product.getId());
       productResponseDTO.setName(product.getName());
       productResponseDTO.setCategory_id(product.getCategory().getId());

       productResponseDTO.setUrlImage(product.getImageThumb());
       productResponseDTO.setCreatedDate(product.getCreatedDate());
       productResponseDTO.setUpdatedDate(product.getUpdatedDate());
       productResponseDTO.setUserCreated(userMapper.toDTO(product.getUserCreated()));
       productResponseDTO.setUserUpdated(userMapper.toDTO(product.getUserUpdated()));
       productResponseDTO.setDescription(product.getDescription());
       double countRate = 0;
        if (product.getReviews() != null){
            productResponseDTO.setReviews(reviewMapper.toDTOs(product.getReviews()));
            for(Reviews rv: product.getReviews()){
                countRate = countRate + rv.getRate();
            }
            countRate = countRate / product.getReviews().size();
        }
        List<VariantDTO> variantDTOList = variantMapper.toListDTO(product.getVariants());
        productResponseDTO.setVariantDTOList(variantDTOList);
        productResponseDTO.setRate(countRate);
        List<ImageProductDTO> imageProductDTOS = imagesProductMapper.toDTOs(product.getImagesProduct());
        productResponseDTO.setImages(imageProductDTOS);
        SpecificationDTO specificationDTO = specificationMapper.toDTO(product.getSpecification());
        productResponseDTO.setSpecificationDTO(specificationDTO);
        return productResponseDTO;
    }

    @Override
    public List<ProductResponseDTO> toResponseDTOs(List<Product> products) {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product p: products){
            productResponseDTOS.add(this.toResponseDTO(p));
        }
        return productResponseDTOS;
    }
}
