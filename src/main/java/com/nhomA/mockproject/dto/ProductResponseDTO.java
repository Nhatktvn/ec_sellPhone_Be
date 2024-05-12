package com.nhomA.mockproject.dto;

import com.nhomA.mockproject.entity.User;
import org.springframework.beans.factory.annotation.Value;

import java.time.ZonedDateTime;
import java.util.List;

public class ProductResponseDTO {
    private Long id;
    private String name;
    private Long category_id;
    private double discount;
    private List<ReviewResponseDTO> reviews;
    private String urlImage;


    private UserDTO userCreated;
    private UserDTO userUpdated;
    private ZonedDateTime createdDate;
    private ZonedDateTime updatedDate;
    private String description;
    private List<ImageProductDTO> images;

    private List<VariantDTO> variantDTOList;
    private SpecificationDTO specificationDTO;
    @Value("0")
    private Double rate;

    public SpecificationDTO getSpecificationDTO() {
        return specificationDTO;
    }

    public void setSpecificationDTO(SpecificationDTO specificationDTO) {
        this.specificationDTO = specificationDTO;
    }

    public List<ImageProductDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageProductDTO> images) {
        this.images = images;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public List<ReviewResponseDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResponseDTO> reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public double getDiscount() {
        return discount;
    }


    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<VariantDTO> getVariantDTOList() {
        return variantDTOList;
    }

    public void setVariantDTOList(List<VariantDTO> variantDTOList) {
        this.variantDTOList = variantDTOList;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public UserDTO getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(UserDTO userCreated) {
        this.userCreated = userCreated;
    }

    public UserDTO getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(UserDTO userUpdated) {
        this.userUpdated = userUpdated;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
