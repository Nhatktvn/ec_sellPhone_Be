package com.nhomA.mockproject.dto;

import java.util.List;

public class ProductRequestDTO {
    private String name;
    private Long category_id;
    private  String imageThumb;

    private  List<String> imagesProduct;
    private String description;
    private List<VariantDTO> variantDTOList;
    private SpecificationDTO specificationDTO;
    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, Long category_id, String imageThumb, List<String> imagesProduct, String description, List<VariantDTO> variantDTOList, SpecificationDTO specificationDTO) {
        this.name = name;
        this.category_id = category_id;
        this.imageThumb = imageThumb;
        this.imagesProduct = imagesProduct;
        this.description = description;
        this.variantDTOList = variantDTOList;
        this.specificationDTO = specificationDTO;
    }

    public SpecificationDTO getSpecificationDTO() {
        return specificationDTO;
    }

    public void setSpecificationDTO(SpecificationDTO specificationDTO) {
        this.specificationDTO = specificationDTO;
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

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
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
