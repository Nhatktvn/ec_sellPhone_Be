package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.CartLineItemResponseDTO;
import com.nhomA.mockproject.entity.CartLineItem;
import com.nhomA.mockproject.mapper.CartLineItemMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartLineItemMapperImpl implements CartLineItemMapper {
    @Override
    public CartLineItemResponseDTO toResponseDTO(CartLineItem cartLineItem) {
        CartLineItemResponseDTO responseDTO = new CartLineItemResponseDTO();
        responseDTO.setId(cartLineItem.getId());
        responseDTO.setCartId(cartLineItem.getCart().getId());
        responseDTO.setProductId(cartLineItem.getProduct().getId());
        responseDTO.setAddedDate(cartLineItem.getAddedDate());
        responseDTO.setDeleted(cartLineItem.isDeleted());
        responseDTO.setQuantity(cartLineItem.getQuantity());
        responseDTO.setUnitPrice(cartLineItem.getUnitPrice());
        responseDTO.setName(cartLineItem.getProduct().getName());
        responseDTO.setUrlImage(cartLineItem.getProduct().getImageThumb());
        responseDTO.setColor(cartLineItem.getColor());
        responseDTO.setStorageCapacity(cartLineItem.getStorage());
        return responseDTO;
    }

    @Override
    public List<CartLineItemResponseDTO> toResponseDTOs(List<CartLineItem> cartLineItems) {
        List<CartLineItemResponseDTO> cartLineItemResponseDTOS = new ArrayList<>();
        for(CartLineItem c : cartLineItems){
            cartLineItemResponseDTOS.add(this.toResponseDTO(c));
        }
        return cartLineItemResponseDTOS;
    }
}

