package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariantRepository extends JpaRepository<Variant, Long> {
//    @Query("SELECT p FROM Variant p WHERE lower(p.color) = lower(?1) and lower(p.storage) = lower(?2) and p.id = ?3")
    Optional<Variant> findByColorAndStorageCapacityAndProductId(String color, String storageCapacity, Long id);
    List<Variant> findByProductId(Long productId);
}
