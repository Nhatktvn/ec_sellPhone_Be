package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.OrderResponseDTO;
import com.nhomA.mockproject.entity.Order;

import java.util.List;

public interface OrderMapper {
    OrderResponseDTO toResponseDTO (Order order);
    List<OrderResponseDTO> toResponseDTOs (List<Order> orders);
}
