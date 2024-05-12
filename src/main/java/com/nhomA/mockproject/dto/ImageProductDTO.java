package com.nhomA.mockproject.dto;

public class ImageProductDTO {
    private Long id;
    private String urlImage;

    public ImageProductDTO(Long id, String urlImage) {
        this.id = id;
        this.urlImage = urlImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
