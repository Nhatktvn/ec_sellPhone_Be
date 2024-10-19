package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.dto.BrandDTO;
import com.nhomA.mockproject.entity.Brand;
import com.nhomA.mockproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String categoryName);
    List<Brand> findByCategoryId(Long categoryId);
    List<Brand> findByCategoryNameContainingIgnoreCase(String categoryName);

    @Query(value = "SELECT * FROM Brand WHERE LOWER(CONVERT(name USING utf8)) LIKE LOWER(CONVERT(CONCAT('%', :name, '%') USING utf8))", nativeQuery = true)
    List<Brand> findByNameWithoutAccent(@Param("name") String name);
}
