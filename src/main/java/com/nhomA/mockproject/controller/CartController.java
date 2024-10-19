package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.AddCartDTO;
import com.nhomA.mockproject.dto.UpdateQuantityCartItemDTO;
import com.nhomA.mockproject.exception.AvailableProductException;
import com.nhomA.mockproject.service.CartService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/cart/add-cart")
    public ResponseEntity<?> addCart(Authentication authentication, @RequestBody AddCartDTO addCartDTO){
        String username = authentication.getName();
        try{
            return new ResponseEntity<> (cartService.addProductToCart(addCartDTO.getIdProduct(), addCartDTO.getQuantity(),username, addCartDTO.getColor(), addCartDTO.getStorage()), HttpStatus.CREATED);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (AvailableProductException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/cart/delete-items/{idCartLine}")
    public ResponseEntity<?> deleteProduct (Authentication authentication, @PathVariable("idCartLine") Long idCartLine){
        String username = authentication.getName();
        try{
            return new ResponseEntity<> (cartService.removeProductCart(idCartLine,username), HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (Exception ex){
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cart/set-quantity")
    public ResponseEntity<?> deleteItem (Authentication authentication, @RequestBody UpdateQuantityCartItemDTO updateQuantityCartItemDTO){
        String username = authentication.getName();
        try{
            return new ResponseEntity<> (cartService.updateQuantityProduct(username,updateQuantityCartItemDTO.getIdCartItem(),updateQuantityCartItemDTO.getQuantity()), HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (Exception ex){
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cart/get-cart")
    public ResponseEntity<?> getCartLineItems (Authentication authentication ){
        String username = authentication.getName();
        try{
            return new ResponseEntity<> (cartService.getAllCartLineItemUsername(username), HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(401));
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
        catch (Exception ex){
            return new ResponseEntity<> (ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
