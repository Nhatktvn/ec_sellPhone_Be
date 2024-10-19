package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusOrderRepository extends JpaRepository<StatusOrder, Long> {
    Optional<StatusOrder> findByName (String name);
}
