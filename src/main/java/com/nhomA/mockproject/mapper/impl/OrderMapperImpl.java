package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.CartLineItemResponseDTO;
import com.nhomA.mockproject.dto.OrderResponseDTO;
import com.nhomA.mockproject.entity.CartLineItem;
import com.nhomA.mockproject.entity.Order;
import com.nhomA.mockproject.mapper.AddressMapper;
import com.nhomA.mockproject.mapper.CartLineItemMapper;
import com.nhomA.mockproject.mapper.OrderMapper;
import com.nhomA.mockproject.mapper.VnPayMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderMapperImpl implements OrderMapper {
    private final CartLineItemMapper cartLineItemMapper;
    private final AddressMapper addressMapper;
    private final VnPayMapper vnPayMapper;

    public OrderMapperImpl(CartLineItemMapper cartLineItemMapper, AddressMapper addressMapper, VnPayMapper vnPayMapper) {
        this.cartLineItemMapper = cartLineItemMapper;
        this.addressMapper = addressMapper;
        this.vnPayMapper = vnPayMapper;
    }

    @Override
    public OrderResponseDTO toResponseDTO(Order order) {
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setAddress(order.getAddress());
        responseDTO.setName(order.getName());
        responseDTO.setPhone(order.getPhoneNumber());
        responseDTO.setTotalPrice(order.getTotalPrice());
        responseDTO.setDeliveryTime(order.getDeliveryTime());
        responseDTO.setStatusOrder(order.getStatusOrder().getName());
        if (order.getVnPayInfo() != null){
            responseDTO.setVnPayResponseDTO(vnPayMapper.toDTO(order.getVnPayInfo()));
        }
        List<CartLineItemResponseDTO> cartLineItemResponseDTOs = new ArrayList<>();
        for(CartLineItem c: order.getCartLineItems()){
            cartLineItemResponseDTOs.add(cartLineItemMapper.toResponseDTO(c));
        }
        responseDTO.setCartLineItemResponseDTOs(cartLineItemResponseDTOs);
        return responseDTO;
    }

    @Override
    public List<OrderResponseDTO> toResponseDTOs(List<Order> orders) {
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for(Order o: orders){
            orderResponseDTOS.add(this.toResponseDTO(o));
        }
        return orderResponseDTOS;
    }
}
