package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.ProductResponseDTO;
import com.nhomA.mockproject.entity.Favourite;
import com.nhomA.mockproject.entity.Product;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.ProductNotFoundException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.mapper.ProductMapper;
import com.nhomA.mockproject.repository.FavouriteRepository;
import com.nhomA.mockproject.repository.ProductRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.FavouriteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public FavouriteServiceImpl(FavouriteRepository favouriteRepository, ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper) {
        this.favouriteRepository = favouriteRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    @Override
    public ProductResponseDTO addFavourite(String username, Long idProduct) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        Optional<Product> existedProduct = productRepository.findById(idProduct);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        if(existedProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found!");
        }
        Optional<Favourite> existedFavourite = favouriteRepository.findByUserIdAndProductId(existedUser.get().getId(),idProduct);
        if(existedFavourite.isPresent()){
            throw new ProductNotFoundException("Favourite is existed!");
        }
        Favourite favourite = new Favourite();
        favourite.setUser(existedUser.get());
        favourite.setProduct(existedProduct.get());
        favouriteRepository.save(favourite);
        return productMapper.toResponseDTO(existedProduct.get());
    }
    @Transactional
    @Override
    public boolean removeFavouriteById(String username, Long idProduct) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        User user = existedUser.get();
        Optional<Favourite> existedFavourite = favouriteRepository.findByUserIdAndProductId(user.getId(),idProduct);
        if(existedFavourite.isEmpty()){
            throw new ProductNotFoundException("Favourite not found!");
        }
        Favourite favourite = existedFavourite.get();
        favouriteRepository.delete(favourite);
        return true;
    }
    @Transactional
    @Override
    public List<ProductResponseDTO> getListFavourite(String username) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        List<Favourite> favourites = existedUser.get().getFavourites();
        List<Product> productFavourite = new ArrayList<>();
        for(Favourite fa: favourites){
            productFavourite.add(fa.getProduct());
        }
        List<ProductResponseDTO> productFavouriteMapper = productMapper.toResponseDTOs(productFavourite);
        return productFavouriteMapper;
    }
}
