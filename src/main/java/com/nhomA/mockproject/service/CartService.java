package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.CartLineItemResponseDTO;
import com.nhomA.mockproject.dto.ProductOutStockDTO;

import java.util.List;

public interface CartService {
    CartLineItemResponseDTO addProductToCart(Long idProduct, int quantity, String username, String color, String storage);
    boolean removeProductCart(Long idCartLine, String username);
    CartLineItemResponseDTO updateQuantityProduct (String username,Long idCartItem,int quantity);
    List<CartLineItemResponseDTO> getAllCartLineItemUsername (String username);
    List<ProductOutStockDTO> checkAvailableOutStock (String username);
}
