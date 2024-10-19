package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.PhoneSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneSpecificationRepository extends JpaRepository<PhoneSpecification, Long> {
    Optional<PhoneSpecification> findByProductId (Long productId);
    void deleteByProductId (Long productId);
}
