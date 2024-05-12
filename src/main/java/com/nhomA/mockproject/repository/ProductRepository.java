package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query ("SELECT p FROM Product p WHERE lower(p.name) = lower(?1)")
    Optional<Product> findByNameExact(String name);

    List<Product> findTop5ByNameContainingIgnoreCase(String keyName);
    List<Product> findByNameContainingIgnoreCase(String nameProduct);
//    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    Page<Product> findByCategoryId(Long idCategory, Pageable pageable);
    List<Product> findByCategoryId(Long idCategory);
}
