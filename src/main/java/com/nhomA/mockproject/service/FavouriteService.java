package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.ProductResponseDTO;

import java.util.List;

public interface FavouriteService {
    ProductResponseDTO addFavourite (String username, Long idProduct);
    boolean removeFavouriteById (String username, Long idProduct);

    List<ProductResponseDTO> getListFavourite (String username);
}
