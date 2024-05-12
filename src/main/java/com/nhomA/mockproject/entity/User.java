package com.nhomA.mockproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.ColumnDefault;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 100, nullable = false, unique = true)
    @Email(message = "Invalid email format")
    private String username;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "created_date")
    private ZonedDateTime createdDate;
    @Column(name = "status")
    @ColumnDefault("false")
    private Boolean status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identification_id", referencedColumnName = "id")
    private Identification identification;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_id", referencedColumnName = "id")
    private ActiveAccount activeAccount;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_reset_id", referencedColumnName = "id")
    private PasswordReset passwordReset;


    @OneToMany(mappedBy = "userCreated", cascade = CascadeType.ALL)
    private List<Product> createdProducts;

    @OneToMany(mappedBy = "userUpdated", cascade = CascadeType.ALL)
    private List<Product> updatedProducts;

    @OneToMany(mappedBy = "userCreated", cascade = CascadeType.ALL)
    private List<Category> createdCategory;

    @OneToMany(mappedBy = "userUpdated", cascade = CascadeType.ALL)
    private List<Category> updatedCategory;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favourite> favourites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    public ActiveAccount getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(ActiveAccount activeAccount) {
        this.activeAccount = activeAccount;
    }

    public PasswordReset getPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(PasswordReset passwordReset) {
        this.passwordReset = passwordReset;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Favourite> favourites) {
        this.favourites = favourites;
    }

    public List<Category> getCreatedCategory() {
        return createdCategory;
    }

    public void setCreatedCategory(List<Category> createdCategory) {
        this.createdCategory = createdCategory;
    }

    public List<Category> getUpdatedCategory() {
        return updatedCategory;
    }

    public void setUpdatedCategory(List<Category> updatedCategory) {
        this.updatedCategory = updatedCategory;
    }

    public List<Product> getCreatedProducts() {
        return createdProducts;
    }

    public void setCreatedProducts(List<Product> createdProducts) {
        this.createdProducts = createdProducts;
    }

    public List<Product> getUpdatedProducts() {
        return updatedProducts;
    }

    public void setUpdatedProducts(List<Product> updatedProducts) {
        this.updatedProducts = updatedProducts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


}
