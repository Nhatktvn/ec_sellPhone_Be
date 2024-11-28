package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.CouponRequestDTO;
import com.nhomA.mockproject.dto.CouponResponseDTO;

import java.util.List;

public interface CouponService {
    CouponResponseDTO createCoupon (CouponRequestDTO couponRequestDTO);

    Boolean receiveCouponByUser (Long idUser, Long idCoupon);
    List<CouponResponseDTO> getAllCoupon ();

    List<CouponResponseDTO> getCouponForUser (Long userId);
    List<CouponResponseDTO> getListCouponUserRecieved(Long userId);
}
