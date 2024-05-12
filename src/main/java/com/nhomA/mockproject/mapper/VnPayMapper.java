package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.OrderPaymentVnPayDTO;
import com.nhomA.mockproject.dto.VnPayResponseDTO;
import com.nhomA.mockproject.entity.VnPayInfo;

public interface VnPayMapper {
    VnPayInfo toEntity (OrderPaymentVnPayDTO orderPaymentVnPayDTO);

    VnPayResponseDTO toDTO (VnPayInfo vnPayInfo);
}
