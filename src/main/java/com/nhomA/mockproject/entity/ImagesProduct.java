package com.nhomA.mockproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "images_product")
public class ImagesProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url_image")
    private String urlImage;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    public ImagesProduct(Long id, String urlImage) {
        this.id = id;
        this.urlImage = urlImage;
    }


    public ImagesProduct() {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
