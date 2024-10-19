package com.nhomA.mockproject.controller;

import com.nhomA.mockproject.dto.BrandDTO;
import com.nhomA.mockproject.exception.BrandNotFoundException;
import com.nhomA.mockproject.service.BrandService;
import com.nhomA.mockproject.service.UploadFileService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BrandController {
    private final BrandService brandService;
    private final UploadFileService uploadFileService;

    public BrandController(BrandService brandService, UploadFileService uploadFileService) {
        this.brandService = brandService;
        this.uploadFileService = uploadFileService;
    }
    @GetMapping("/brand/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(brandService.getBrandById(id),HttpStatus.OK);
        }
        catch (BrandNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/brands-sort")
    public ResponseEntity<?> getBrandPagingAndSorting(@RequestParam(value = "pageNo",defaultValue = "0")int pageNo,
                                            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                            @RequestParam(value = "sortBy",defaultValue = "id")String sortBy,
                                            @RequestParam(value = "sortDir",defaultValue = "asc")String sorDir){
        try {
            return new ResponseEntity<>(brandService.getBrandPagingAndSort(pageNo,pageSize,sortBy,sorDir),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/brandsByCategory")
    public ResponseEntity<?> getBrandsByCategoryId(@RequestParam("categoryId") Long categoryId){
        try {
            return new ResponseEntity<>(brandService.getBrandsByCategoryId(categoryId),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/brandsByCategoryName")
    public ResponseEntity<?> getBrandsByCategoryName(@RequestParam("categoryName") String categoryName){
        try {
            return new ResponseEntity<>(brandService.getBrandsByCategoryName(categoryName),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrand(){
        try {
            return new ResponseEntity<>(brandService.getBrands(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/brand")
    public  ResponseEntity<?> createBrand(Authentication authentication, @RequestParam("image")MultipartFile multipartFile, @RequestParam("name") String name,
                                                 @RequestParam("description") String description, @RequestParam("categoryId") Long categoryId) throws IOException {
        String username = authentication.getName();
        String imageURL = uploadFileService.uploadFile(multipartFile);
        BrandDTO brandDTO = new BrandDTO(name,description,imageURL,categoryId);
        List<String> filenames = new ArrayList<>();
        try {
            return new ResponseEntity<>(brandService.createBrand(brandDTO,username),HttpStatus.OK);
//            return new ResponseEntity<>(filenames,HttpStatus.OK);
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
    @PutMapping("/admin/brand/{id}")
    public ResponseEntity<?> updateBrand (Authentication authentication,@PathVariable Long id,@RequestParam(name = "image", required = false)MultipartFile multipartFile, @RequestParam("name") String name,
                                             @RequestParam("description") String description, @RequestParam("categoryId") Long categoryId) throws IOException{
        String username = authentication.getName();
        String imageUrl = "";
        if(multipartFile != null){
            imageUrl = uploadFileService.uploadFile(multipartFile);
        }
        BrandDTO brandDTO = new BrandDTO(name,description,imageUrl,categoryId);
        try {
            return new ResponseEntity<>(brandService.updateBrandById(username,id, brandDTO),HttpStatus.OK);
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
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/admin/brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id){
        try {
            return new ResponseEntity<>(brandService.deleteBrandById(id),HttpStatus.OK);
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
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
