package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.OrderPaymentVnPayDTO;
import com.nhomA.mockproject.dto.OrderRequestDTO;
import com.nhomA.mockproject.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO order (String username, OrderRequestDTO orderRequestDTO);

    OrderResponseDTO orderPaymentVnPay (String username, OrderPaymentVnPayDTO paymentVnPayDTO);
    List<OrderResponseDTO> getAllOrder (int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponseDTO setStatusOrder(Long orderId, Long statusOrderId);
    OrderResponseDTO getDetailOrder(String username, Long orderId);
    List<OrderResponseDTO> getOrderByUser(String username);

    boolean cancelOrder(Long idOrder, String username);
    boolean receivedProduct (Long idOrder, String username);
}
