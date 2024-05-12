package com.nhomA.mockproject.dto;

public class ReviewRequestDTO {
    private Long idProduct;
    private String username;
    private int rate;
    private String contentReviews;
    private String urlImage;

    public ReviewRequestDTO(Long idProduct, String username, int rate, String contentReviews, String urlImage) {
        this.idProduct = idProduct;
        this.username = username;
        this.rate = rate;
        this.contentReviews = contentReviews;
        this.urlImage = urlImage;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getContentReviews() {
        return contentReviews;
    }

    public void setContentReviews(String contentReviews) {
        this.contentReviews = contentReviews;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
