package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.LaptopSpecification;
import com.nhomA.mockproject.entity.PhoneSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaptopSpecificationRepository extends JpaRepository<LaptopSpecification, Long> {
    Optional<LaptopSpecification> findByProductId (Long productId);
    void deleteByProductId (Long productId);
}
