package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.CouponRequestDTO;
import com.nhomA.mockproject.dto.CouponResponseDTO;
import com.nhomA.mockproject.entity.Coupon;
import com.nhomA.mockproject.entity.CouponUser;
import com.nhomA.mockproject.mapper.CouponMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CouponMapperImpl implements CouponMapper {
    @Override
    public CouponResponseDTO toDTO(Coupon coupon) {
        CouponResponseDTO couponResponseDTO = new CouponResponseDTO();
        couponResponseDTO.setId(coupon.getId());
        couponResponseDTO.setCodeCoupon(coupon.getCodeCoupon());
        couponResponseDTO.setTypeCoupon(coupon.getTypeCoupon());
        couponResponseDTO.setCouponValue(coupon.getCouponValue());
        couponResponseDTO.setMinimumAmount(coupon.getMinimumAmount());
        couponResponseDTO.setQuantity(coupon.getQuantity());
        couponResponseDTO.setStartTime(coupon.getStartTime());
        couponResponseDTO.setEndTime(coupon.getEndTime());
        return couponResponseDTO;
    }

    @Override
    public List<CouponResponseDTO> toDTOs(List<Coupon> coupons) {
        List<CouponResponseDTO> couponResponseDTOS = new ArrayList<>();
        for(Coupon c : coupons){
            CouponResponseDTO couponResponseDTO = this.toDTO(c);
            couponResponseDTOS.add(couponResponseDTO);
        }
        return couponResponseDTOS;
    }

    @Override
    public Coupon toEntity(CouponRequestDTO couponRequestDTO) {
        Coupon coupon = new Coupon();
        coupon.setCodeCoupon(couponRequestDTO.getCodeCoupon());
        coupon.setTypeCoupon(couponRequestDTO.getTypeCoupon());
        coupon.setCouponValue(couponRequestDTO.getCouponValue());
        coupon.setMinimumAmount(couponRequestDTO.getMinimumAmount());
        coupon.setQuantity(couponRequestDTO.getQuantity());
        coupon.setStartTime(couponRequestDTO.getStartTime());
        coupon.setEndTime(couponRequestDTO.getEndTime());
        return coupon;
    }
}
