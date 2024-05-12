package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
