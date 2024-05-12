package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId (Long userId);
    Optional<Order> findByIdAndUserId (Long id, Long userId);
}
