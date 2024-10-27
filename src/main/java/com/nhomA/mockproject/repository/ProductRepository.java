package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query ("SELECT p FROM Product p WHERE lower(p.name) = lower(?1)")
    Optional<Product> findByNameExact(String name);

    List<Product> findTop5ByNameContainingIgnoreCase(String keyName);
    List<Product> findByNameContainingIgnoreCase(String nameProduct);
//    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    Page<Product> findTop5ByNameContainingIgnoreCase(String keyName, Pageable pageable);
    Page<Product> findByBrandId(Long idBrand, Pageable pageable);
    List<Product> findByBrandId(Long idBrand);
    List<Product> findByIdIn(List<Long> ids);
//    @Query("SELECT * FROM product p JOIN variant ON p.id = variant.product_id WHERE (:category IS NULL OR p.category_id = :categoryId) " +
//            "AND (:brandId IS NULL OR p.brand_id = :brandId) " +
//            "AND (:minPrice IS NULL OR v.sell_price >= :minPrice) " +
//            "AND (:maxPrice IS NULL OR v.sell_price <= :maxPrice) " +
//            "AND (:storage IS NULL OR v.storage_capacity = :storageCapacity)")
    @Query("SELECT p FROM Product p JOIN p.variants v JOIN p.category c JOIN p.brand b  WHERE (:category IS NULL OR c.id = :category) " +
            "AND (b.id IN :brand) " +
            "AND (:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')))" +
            "AND (:minPrice IS NULL OR v.sellPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR v.sellPrice <= :maxPrice) " +
            "AND (:storage IS NULL OR v.storageCapacity = :storage)")
    List<Product> findByFilters(@Param("category") Long category,
                                @Param("brand") List<Long> brand,
                                @Param("search") String search,
                                @Param("minPrice") double minPrice,
                                @Param("maxPrice") double maxPrice,
                                @Param("storage") String storage);

    @Query("SELECT p FROM Product p JOIN p.variants v JOIN p.category c JOIN p.brand b  WHERE (:category IS NULL OR c.id = :category) " +
            "AND (:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')))" +
            "AND (:minPrice IS NULL OR v.sellPrice >= :minPrice) " +
            "AND (:maxPrice IS NULL OR v.sellPrice <= :maxPrice) " +
            "AND (:storage IS NULL OR v.storageCapacity = :storage)")
    List<Product> findByFiltersNoBrand(@Param("category") Long category,
                                       @Param("search") String search,
                                @Param("minPrice") double minPrice,
                                @Param("maxPrice") double maxPrice,
                                @Param("storage") String storage);
}
