package com.nhomA.mockproject.mapper.impl;

import com.google.gson.Gson;
import com.nhomA.mockproject.dto.*;
import com.nhomA.mockproject.entity.*;
import com.nhomA.mockproject.mapper.*;
import com.nhomA.mockproject.repository.CategoryRepository;
import com.nhomA.mockproject.repository.LaptopSpecificationRepository;
import com.nhomA.mockproject.repository.PhoneSpecificationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductMapperImpl implements ProductMapper {
    private final UserMapper userMapper;
    private final VariantMapper variantMapper;
    private final ImagesProductMapper imagesProductMapper;
    private final PhoneSpecificationMapper phoneSpecificationMapper;
    private final PhoneSpecificationRepository phoneSpecificationRepository;
    private final CategoryRepository categoryRepository;
    private final LaptopSpecificationMapper laptopSpecificationMapper;
    private final LaptopSpecificationRepository laptopSpecificationRepository;
    public ProductMapperImpl(UserMapper userMapper, VariantMapper variantMapper, ImagesProductMapper imagesProductMapper, PhoneSpecificationMapper phoneSpecificationMapper, PhoneSpecificationRepository phoneSpecificationRepository, CategoryRepository categoryRepository, LaptopSpecificationMapper laptopSpecificationMapper, LaptopSpecificationRepository laptopSpecificationRepository, ReviewMapper reviewMapper) {
        this.userMapper = userMapper;
        this.variantMapper = variantMapper;
        this.imagesProductMapper = imagesProductMapper;
        this.phoneSpecificationMapper = phoneSpecificationMapper;
        this.phoneSpecificationRepository = phoneSpecificationRepository;
        this.categoryRepository = categoryRepository;
        this.laptopSpecificationMapper = laptopSpecificationMapper;
        this.laptopSpecificationRepository = laptopSpecificationRepository;
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
       productResponseDTO.setBrand_id(product.getBrand().getId());
       productResponseDTO.setCategory_name(product.getCategory().getName());
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
//        List<VariantDTO> variantDTOList = variantMapper.toListDTO(variantOfPhoneRepository.findByProductId(product.getId()));
        productResponseDTO.setVariantDTOList(variantDTOList);
        productResponseDTO.setRate(countRate);
        List<ImageProductDTO> imageProductDTOS = imagesProductMapper.toDTOs(product.getImagesProduct());
        productResponseDTO.setImages(imageProductDTOS);
//        Optional<PhoneSpecification> phoneSpecification = phoneSpecificationRepository.findByProductId(product.getId());
//        PhoneSpecificationDTO phoneSpecificationDTO = specificationMapper.toDTO(phoneSpecification.get());
//        productResponseDTO.setSpecificationDTO(phoneSpecificationDTO);
        SpecificationResponseDTO specificationResponseDTO = getSpecificationProduct(product);
        productResponseDTO.setSpecificationDTO(specificationResponseDTO);
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


    public void saveSpecification (Long category_id, Product product, String specificationString) {
        Gson gson = new Gson();
        Optional<Category> category = categoryRepository.findById(category_id);
//        SpecificationDTO specificationDTO = gson.fromJson(productRequestDTO.getSpecification(), SpecificationDTO.class);
        switch (category.get().getName().toLowerCase()) {
            case "điện thoại":
                PhoneSpecificationDTO phoneSpecificationDTO = gson.fromJson(specificationString, PhoneSpecificationDTO.class);
                PhoneSpecification phoneSpecification = phoneSpecificationMapper.toEntity(phoneSpecificationDTO);
                phoneSpecification.setProduct(product);
                phoneSpecificationRepository.save(phoneSpecification);
                return;
            case "laptop":
                LaptopSpecificationDTO laptopSpecificationDTO = gson.fromJson(specificationString, LaptopSpecificationDTO.class);
                LaptopSpecification laptopSpecification = laptopSpecificationMapper.toEntity(laptopSpecificationDTO);
                laptopSpecification.setProduct(product);
                laptopSpecificationRepository.save(laptopSpecification);
                return;
            default:
                return;
        }
    }

    public SpecificationResponseDTO getSpecificationProduct (Product product){
        SpecificationResponseDTO SpecificationResponseDTO = new SpecificationResponseDTO();
        Category category = product.getCategory();
        switch (category.getName().toLowerCase()) {
            case "điện thoại":
                Optional<PhoneSpecification> phoneSpecification = phoneSpecificationRepository.findByProductId(product.getId());
                PhoneSpecificationDTO phoneSpecificationDTO = phoneSpecificationMapper.toDTO(phoneSpecification.get());
                return phoneSpecificationDTO;
            case "laptop":
                Optional<LaptopSpecification> laptopSpecification = laptopSpecificationRepository.findByProductId(product.getId());
                LaptopSpecificationDTO laptopSpecificationDTO = laptopSpecificationMapper.toDTO(laptopSpecification.get());
                return laptopSpecificationDTO;
            default:
                return SpecificationResponseDTO;
        }
    }
}
