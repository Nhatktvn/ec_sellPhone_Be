package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponUserRepository extends JpaRepository<CouponUser, Long> {
    Optional<CouponUser> findByCouponIdAndUserId (Long couponId, Long userId);
    List<CouponUser> findByUserId(Long userId);
    List<CouponUser> findByUserIdAndUsed (Long userId, boolean used);

}
