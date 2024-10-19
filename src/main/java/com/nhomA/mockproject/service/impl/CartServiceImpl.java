package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.CartLineItemResponseDTO;
import com.nhomA.mockproject.entity.*;
import com.nhomA.mockproject.exception.AvailableProductException;
import com.nhomA.mockproject.exception.CartLineItemNotFoundException;
import com.nhomA.mockproject.exception.VariantProductNotFoundException;
import com.nhomA.mockproject.mapper.CartLineItemMapper;
import com.nhomA.mockproject.repository.*;
import com.nhomA.mockproject.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartLineItemRepository cartLineItemRepository;
    private final CartLineItemMapper cartLineItemMapper;
    private final VariantRepository variantRepository;

    public CartServiceImpl(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, CartLineItemRepository cartLineItemRepository, CartLineItemMapper cartLineItemMapper, VariantRepository variantRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartLineItemRepository = cartLineItemRepository;
        this.cartLineItemMapper = cartLineItemMapper;
        this.variantRepository = variantRepository;
    }

    @Transactional
    @Override
    public CartLineItemResponseDTO addProductToCart(Long idProduct, int quantity, String username,  String color, String storage) {
        Optional<User> emptyUser =  userRepository.findByUsername(username);
        User user = emptyUser.get();
        Cart cart = user.getCart();
        Optional<Product> emptyProduct = productRepository.findById(idProduct);
        if(emptyProduct.isEmpty()){
            throw new VariantProductNotFoundException("Variant product is not found!");
        }
        Optional<Variant> existedVariant = variantRepository.findByColorAndStorageCapacityAndProductId(color,storage,idProduct);
        if(existedVariant.isEmpty()){
            throw new VariantProductNotFoundException("Variant not found");
        }
        Variant variant = existedVariant.get();
        if(quantity > variant.getAvailable()){
            throw  new AvailableProductException("Product quantity is not enough");
        }
        Optional<CartLineItem> emptyCartLineItem = cartLineItemRepository.findByCartIdAndProductIdAndIsDeletedAndColorAndStorage(cart.getId(),idProduct,false,color,storage);
        //create new cart_line_item
        if (emptyCartLineItem.isEmpty()){
            CartLineItem cartLineItem = new CartLineItem();
            cartLineItem.setProduct(emptyProduct.get());
            cartLineItem.setCart(cart);
            cartLineItem.setQuantity(quantity);
            cartLineItem.setSellPrice(variant.getSellPrice());
            cartLineItem.setOriginalPrice(variant.getOriginalPrice());
            cartLineItem.setColor(variant.getColor());
            cartLineItem.setStorage(variant.getStorageCapacity());
            cartLineItem.setAddedDate(ZonedDateTime.now());
            cartLineItem.setCart(cart);
            CartLineItem saveCartLineItem = cartLineItemRepository.save(cartLineItem);
            System.out.println(saveCartLineItem);
            CartLineItemResponseDTO cartLineItemResponseDTO = cartLineItemMapper.toResponseDTO(saveCartLineItem);
            return cartLineItemResponseDTO;
        }
        CartLineItem cartLineItem = emptyCartLineItem.get();
        int quantityNew = cartLineItem.getQuantity() + quantity;
        cartLineItem.setQuantity(quantityNew);
        CartLineItem saveCartLineItem = cartLineItemRepository.save(cartLineItem);
        CartLineItemResponseDTO cartLineItemResponseDTO = cartLineItemMapper.toResponseDTO(saveCartLineItem);
        List<CartLineItem> cartLineItems = cartLineItemRepository.findByCartIdAndIsDeletedOrderById(cart.getId(), false);
        cartLineItemResponseDTO.setCountCartLine(cartLineItems.size());
        return cartLineItemResponseDTO;
    }

    @Transactional
    @Override
    public boolean removeProductCart(Long idCartLine, String username) {
        Optional<User> emptyUser =  userRepository.findByUsername(username);
        User user = emptyUser.get();
        Cart cart = user.getCart();
        Optional<CartLineItem> cartLineItem = cartLineItemRepository.findByIdAndCartId(idCartLine,cart.getId());
        if(cartLineItem.isEmpty()){
            throw new CartLineItemNotFoundException("Cart line item not found!");
        }
        CartLineItem cartLineItemDelete = cartLineItem.get();
        cartLineItemRepository.delete(cartLineItemDelete);
        return true;
    }

    @Transactional
    @Override
    public CartLineItemResponseDTO updateQuantityProduct(String username, Long idCartItem, int quantity) {
        Optional<User> emptyUser =  userRepository.findByUsername(username);
        User user = emptyUser.get();
        Cart cart = user.getCart();
        Optional<CartLineItem> emptyCartLineItem = cartLineItemRepository.findById(idCartItem);
        if(emptyCartLineItem.isEmpty()){
            throw new CartLineItemNotFoundException("Cart line item not found");
        }

        CartLineItem cartLineItem = emptyCartLineItem.get();
        if(quantity == 0){
            cartLineItemRepository.delete(cartLineItem);
            return null;
        }
        cartLineItem.setQuantity(quantity);
        CartLineItem saveCartLineItem = cartLineItemRepository.save(cartLineItem);
        CartLineItemResponseDTO cartLineItemResponseDTO = cartLineItemMapper.toResponseDTO(saveCartLineItem);
        List<CartLineItem> cartLineItems = cartLineItemRepository.findByCartIdAndIsDeleted(cart.getId(), false);
        cartLineItemResponseDTO.setCountCartLine(cartLineItems.size());
        return cartLineItemMapper.toResponseDTO(saveCartLineItem);
    }
    @Transactional
    @Override
    public List<CartLineItemResponseDTO> getAllCartLineItemUsername(String username) {
        Optional<User> emptyUser =  userRepository.findByUsername(username);
        User user = emptyUser.get();
        Cart cart = user.getCart();
        List<CartLineItem> cartLineItems = cartLineItemRepository.findByCartIdAndIsDeletedOrderById(cart.getId(), false);
        List<CartLineItemResponseDTO> cartLineItemResponseDTOS = cartLineItemMapper.toResponseDTOs(cartLineItems);
        return cartLineItemResponseDTOS;
    }
}
