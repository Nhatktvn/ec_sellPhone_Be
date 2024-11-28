package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.CouponRequestDTO;
import com.nhomA.mockproject.dto.CouponResponseDTO;
import com.nhomA.mockproject.entity.Coupon;

import java.util.List;

public interface CouponMapper {
    CouponResponseDTO toDTO (Coupon coupon);
    List<CouponResponseDTO> toDTOs (List<Coupon> coupons);
    Coupon toEntity (CouponRequestDTO couponRequestDTO);
}
