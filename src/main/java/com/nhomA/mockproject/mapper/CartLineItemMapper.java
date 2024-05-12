package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.CartLineItemResponseDTO;
import com.nhomA.mockproject.entity.CartLineItem;

import java.util.List;

public interface CartLineItemMapper {
    CartLineItemResponseDTO toResponseDTO (CartLineItem cartLineItem);
    List<CartLineItemResponseDTO> toResponseDTOs (List<CartLineItem> cartLineItems);
}
