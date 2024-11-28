package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.CouponRequestDTO;
import com.nhomA.mockproject.dto.CouponResponseDTO;
import com.nhomA.mockproject.entity.Coupon;
import com.nhomA.mockproject.entity.CouponUser;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.CouponException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.mapper.CouponMapper;
import com.nhomA.mockproject.repository.CouponRepository;
import com.nhomA.mockproject.repository.CouponUserRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.CouponService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;
    private final CouponUserRepository couponUserRepository;
    private final UserRepository userRepository;

    public CouponServiceImpl(CouponMapper couponMapper, CouponRepository couponRepository, CouponUserRepository couponUserRepository, UserRepository userRepository) {
        this.couponMapper = couponMapper;
        this.couponRepository = couponRepository;
        this.couponUserRepository = couponUserRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public CouponResponseDTO createCoupon(CouponRequestDTO couponRequestDTO) {
        Coupon coupon = couponMapper.toEntity(couponRequestDTO);
        couponRepository.save(coupon);
        return couponMapper.toDTO(coupon);
    }

    @Override
    public Boolean receiveCouponByUser(Long idUser, Long idCoupon) {
        Optional<Coupon> couponExited = couponRepository.findById(idCoupon);
        if(couponExited.isEmpty()){
            throw new CouponException("Mã coupon không hợp lệ");
        }
        Optional<User> userExited = userRepository.findById(idUser);
        if(userExited.isEmpty()){
            throw new UserNotFoundException("Không tìm thấy user");
        }
        Optional<CouponUser> couponUserExisted = couponUserRepository.findByCouponIdAndUserId(idCoupon,idUser);
        if(couponUserExisted.isPresent()){
            throw new CouponException("Người dùng đã nhận coupon này trước đó rồi");
        }
        Coupon coupon = couponExited.get();
        CouponUser couponUser = new CouponUser();
        couponUser.setCoupon(coupon);
        couponUser.setUser(userExited.get());
        coupon.setQuantity(coupon.getQuantity() - 1);
        couponRepository.save(coupon);
        couponUserRepository.save(couponUser);
        return true;
    }

    @Override
    public List<CouponResponseDTO> getAllCoupon() {
        List<Coupon> coupons = couponRepository.findAll();
        return couponMapper.toDTOs(coupons);
    }

    @Override
    public List<CouponResponseDTO> getCouponForUser(Long userId) {
        List<Coupon> coupons = couponRepository.findAll();
        List<Coupon> couponsForUser = new ArrayList<>();
        for(Coupon c: coupons){
            Optional<CouponUser> couponUser = couponUserRepository.findByCouponIdAndUserId(c.getId(), userId);
            if(couponUser.isEmpty()){
                couponsForUser.add(c);
            }
        }
        return couponMapper.toDTOs(couponsForUser);
    }

    @Override
    public List<CouponResponseDTO> getListCouponUserRecieved(Long userId) {
        List<CouponUser> couponUsers = couponUserRepository.findByUserIdAndUsed(userId, false);
        List<Coupon> coupons = new ArrayList<>();
        for(CouponUser cu: couponUsers){
            coupons.add(cu.getCoupon());
        }
        return couponMapper.toDTOs(coupons);
    }
}
