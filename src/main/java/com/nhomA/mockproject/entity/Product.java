package com.nhomA.mockproject.entity;

import com.paypal.api.payments.Phone;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "image_thumb")
    private String imageThumb;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImagesProduct> imagesProduct;

    @ManyToOne
    @JoinColumn(name = "user_created", referencedColumnName = "id")
    private User userCreated;

    @ManyToOne
    @JoinColumn(name = "user_updated", referencedColumnName = "id")
    private User userUpdated;
    @OneToMany(mappedBy = "product")
    private List<CartLineItem> cartLineItems;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "description",length = -1)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Favourite> favourites;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Variant> variants;


//    @OneToOne(mappedBy = "phoneSpecification", cascade = CascadeType.ALL)
//    private PhoneSpecification phoneSpecification;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "phoneSpecification_id", referencedColumnName = "id")
//    private PhoneSpecification phoneSpecification;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "specification_id", referencedColumnName = "id")
//    private PhoneSpecification phoneSpecification;

//    public PhoneSpecification getSpecification() {
//        return phoneSpecification;
//    }
//
//    public void setSpecification(PhoneSpecification phoneSpecification) {
//        this.phoneSpecification = phoneSpecification;
//    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ImagesProduct> getImagesProduct() {
        return imagesProduct;
    }

    public void setImagesProduct(List<ImagesProduct> imagesProduct) {
        this.imagesProduct = imagesProduct;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variantOfPhones) {
        this.variants = variantOfPhones;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public User getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(User userUpdated) {
        this.userUpdated = userUpdated;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<CartLineItem> getCartLineItems() {
        return cartLineItems;
    }

    public void setCartLineItems(List<CartLineItem> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }
}
