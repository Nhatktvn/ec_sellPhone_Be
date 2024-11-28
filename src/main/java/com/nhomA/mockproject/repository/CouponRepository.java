package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
