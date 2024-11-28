package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.OrderPaymentVnPayDTO;
import com.nhomA.mockproject.dto.OrderRequestDTO;
import com.nhomA.mockproject.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderResponseDTO order (String username, OrderRequestDTO orderRequestDTO);

    OrderResponseDTO orderPaymentVnPay (String username, String codeOrder, OrderPaymentVnPayDTO paymentVnPayDTO);
    List<OrderResponseDTO> getAllOrder (int pageNo, int pageSize, String sortBy, String sortDir);
    OrderResponseDTO setStatusOrder(Long orderId, String statusOrderName);
    OrderResponseDTO getDetailOrder(String username, Long orderId);
    OrderResponseDTO getDetailOrderByCodeOrder(String username, String codeOrder);
    List<OrderResponseDTO> getOrderByUser(String username);

    boolean cancelOrder(Long idOrder, String username);
    boolean receivedProduct (Long idOrder, String username);
}
