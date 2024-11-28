package com.nhomA.mockproject.dto;

import java.util.List;

public class ProductRequestDTO {
    private String name;
    private Long  brand_id;
    private Long category_id;
    private  String imageThumb;
    private String imageSpec;
    private  List<String> imagesProduct;
    private String description;
    private List<VariantDTO> variantDTOList;
    private String specification;
    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, Long brand_id, Long category_id, String imageThumb,String imageSpec, List<String> imagesProduct, String description, List<VariantDTO> variantDTOList, String specification) {
        this.name = name;
        this.brand_id = brand_id;
        this.category_id = category_id;
        this.imageThumb = imageThumb;
        this.imageSpec = imageSpec;
        this.imagesProduct = imagesProduct;
        this.description = description;
        this.variantDTOList = variantDTOList;
        this.specification = specification;
    }

    public String getImageSpec() {
        return imageSpec;
    }

    public void setImageSpec(String imageSpec) {
        this.imageSpec = imageSpec;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public List<String> getImagesProduct() {
        return imagesProduct;
    }

    public void setImagesProduct(List<String> imagesProduct) {
        this.imagesProduct = imagesProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VariantDTO> getVariantDTOList() {
        return variantDTOList;
    }

    public void setVariantDTOList(List<VariantDTO> variantDTOList) {
        this.variantDTOList = variantDTOList;
    }
}
