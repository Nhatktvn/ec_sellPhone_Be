package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.OrderPaymentVnPayDTO;
import com.nhomA.mockproject.dto.VnPayResponseDTO;
import com.nhomA.mockproject.entity.VnPayInfo;
import com.nhomA.mockproject.mapper.VnPayMapper;
import org.springframework.stereotype.Component;

@Component
public class VnPayMapperImpl implements VnPayMapper {
    @Override
    public VnPayInfo toEntity(OrderPaymentVnPayDTO orderPaymentVnPayDTO) {
        VnPayInfo vnPayInfo = new VnPayInfo(orderPaymentVnPayDTO.getVnpAmount(), orderPaymentVnPayDTO.getVnpBankCode(), orderPaymentVnPayDTO.getVnpTransactionNo(), orderPaymentVnPayDTO.getVnpOrderInfo(), orderPaymentVnPayDTO.getVnpSecureHash(), orderPaymentVnPayDTO.getVnpPayDate(), orderPaymentVnPayDTO.getVnpTxnRef());
        return vnPayInfo;
    }

    @Override
    public VnPayResponseDTO toDTO(VnPayInfo vnPayInfo) {
        VnPayResponseDTO VnPayResponseDTO = new VnPayResponseDTO(vnPayInfo.getVnpAmount(), vnPayInfo.getVnpBankCode(), vnPayInfo.getVnpTransactionNo(), vnPayInfo.getVnpOrderInfo(), vnPayInfo.getVnpSecureHash(), vnPayInfo.getVnpPayDate(), vnPayInfo.getVnpTxnRef());
        return VnPayResponseDTO;
    }
}
