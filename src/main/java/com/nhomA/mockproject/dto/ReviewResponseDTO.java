package com.nhomA.mockproject.dto;

import java.time.ZonedDateTime;

public class ReviewResponseDTO {
    private Long id;
    private int rate;
    private String name;
    private String username;
    private String contentReviews;
    private String urlImage;
    private ZonedDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
