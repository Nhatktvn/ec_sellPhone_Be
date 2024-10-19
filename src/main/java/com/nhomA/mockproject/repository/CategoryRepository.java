package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Brand;
import com.nhomA.mockproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);
    Optional<Category> findById(Long categoryId);
}
