package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.CategoryDTO;
import com.nhomA.mockproject.entity.Category;
import com.nhomA.mockproject.service.CategoryService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/admin/category")
    public ResponseEntity<?> createCategory(Authentication authentication, @RequestBody CategoryDTO categoryDTO) throws IOException {
        String username = authentication.getName();
        try {
            return new ResponseEntity<>(categoryService.createCategory(categoryDTO,username), HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategoryByName(@RequestParam String categoryName) throws IOException {
        try {
            return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory() throws IOException {
        try {
            return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<?> deleteCategory(Authentication authentication, @PathVariable Long id) throws IOException {
        String username = authentication.getName();
        try {
            return new ResponseEntity<>(categoryService.deleteCategoryById(id), HttpStatus.OK);
        }
        catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (ExpiredJwtException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (AccessDeniedException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
