package com.nhomA.mockproject.dto;

public class BrandDTO {
    private Long id;
    private String name;
    private String description;
    private String urlImage;
    private Long categoryId;


    public BrandDTO() {
    }

    public BrandDTO(String name, String description, String urlImage, Long categoryId) {
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
