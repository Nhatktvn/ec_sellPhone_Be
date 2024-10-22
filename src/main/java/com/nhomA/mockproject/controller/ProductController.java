package com.nhomA.mockproject.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhomA.mockproject.dto.LaptopSpecificationDTO;
import com.nhomA.mockproject.dto.ProductRequestDTO;
import com.nhomA.mockproject.dto.PhoneSpecificationDTO;
import com.nhomA.mockproject.dto.VariantDTO;
import com.nhomA.mockproject.exception.BrandNotFoundException;
import com.nhomA.mockproject.exception.ProductNotFoundException;
import com.nhomA.mockproject.service.ProductService;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final UploadFileService uploadFileService;

    public ProductController(ProductService productService, UploadFileService uploadFileService) {
        this.productService = productService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductById(@RequestParam("searchName") String searchName){
        try {
            return new ResponseEntity<>(productService.searchProduct(searchName), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product")
    public ResponseEntity<?> getProductByNameExact(@RequestParam("name") String name){
        try {
            return new ResponseEntity<>(productService.getProductByNameExact(name), HttpStatus.OK);
        }
        catch (ProductNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/get-all")
    public ResponseEntity<?> getAllProduct(){
        try {
            return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductPage(@RequestParam(value = "pageNo",defaultValue = "0")int pageNo,
                                            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                            @RequestParam(value = "sortBy",defaultValue = "id")String sortBy,
                                            @RequestParam(value = "sortDir",defaultValue = "asc")String sorDir,
                                            @RequestParam(value = "idCategory", defaultValue = "") Long idCategory
    ){
        try {
            return new ResponseEntity<>(productService.getProductPage(pageNo,pageSize,sortBy,sorDir, idCategory),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/products/products-by-cate")
    public ResponseEntity<?> getAllProductByCateGory(@RequestParam("cateName") String categoryName){
        try {
            return new ResponseEntity<>(productService.getProductsByCategory(categoryName),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/products")
    public  ResponseEntity<?> createProduct(Authentication authentication,@RequestParam("imageThumb")MultipartFile imageThumb, @RequestParam("name") String name,
                                            @RequestParam("brand_id") Long brandId,
                                            @RequestParam("category_id") Long categoryId,
                                            @RequestParam("description") String description,
                                            @RequestParam("variant") String variantDTOs,@RequestParam("specification") String specification, @RequestParam("imagesProduct")MultipartFile[] imagesProduct) throws IOException {
        String username = authentication.getName();
        try {
            Gson gson = new Gson();
            Type listTypeVariant = new TypeToken<List<VariantDTO>>() {}.getType();
            List<VariantDTO> variantDTOList = gson.fromJson(variantDTOs, listTypeVariant) ;

//            LaptopSpecificationDTO laptopSpecificationDTO = gson.fromJson(specification, LaptopSpecificationDTO.class);
            List<String> listStringImages= new ArrayList<>();
            String  thumbImage = uploadFileService.uploadFile(imageThumb);
            for (MultipartFile file : imagesProduct) {
                if (!file.isEmpty()) {
                    String  imageUrl = uploadFileService.uploadFile(file);
                    listStringImages.add(imageUrl);
                }
            }
            ProductRequestDTO productRequestDTO = new ProductRequestDTO(name,brandId,categoryId,thumbImage,listStringImages,description,variantDTOList,specification);
            return new ResponseEntity<>(productService.createProduct(username, productRequestDTO),HttpStatus.OK);
//            return new ResponseEntity<>(laptopSpecificationDTO,HttpStatus.OK);
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
        catch (BrandNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/products-by-file")
    public  ResponseEntity<?> createProductByFile(Authentication authentication, @RequestParam("imageThumb")MultipartFile imageThumb, @RequestParam("name") String name,
                                                  @RequestParam("brand_id") Long brandId,
                                                  @RequestParam("category_id") Long categoryId,
                                                   @RequestParam("description") String description,
                                                  @RequestParam("variant") String variantDTOs,@RequestParam("specification") String specification, @RequestParam("imagesProduct")MultipartFile[] imagesProduct) throws IOException {
        String username = authentication.getName();

        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<VariantDTO>>() {}.getType();
            List<VariantDTO> variantDTOList = gson.fromJson(variantDTOs, listType) ;
//            PhoneSpecificationDTO phoneSpecificationDTO = gson.fromJson(specification, PhoneSpecificationDTO.class);
            List<String> listStringImages= new ArrayList<>();
            String  thumbImage = uploadFileService.uploadFile(imageThumb);
            for (MultipartFile file : imagesProduct) {
                if (!file.isEmpty()) {
                    String  imageUrl = uploadFileService.uploadFile(file);
                    listStringImages.add(imageUrl);
                }
            }
            ProductRequestDTO productRequestDTO = new ProductRequestDTO(name,brandId,categoryId,thumbImage,listStringImages,description,variantDTOList, specification);
            return new ResponseEntity<>(productService.createProduct(username, productRequestDTO),HttpStatus.OK);
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
        catch (BrandNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/admin/products/{id}")
    public ResponseEntity<?> updateProduct (Authentication authentication,@PathVariable Long id, @RequestParam(value = "imageThumb", required = false)MultipartFile imageThumb, @RequestParam("name") String name,
                                            @RequestParam("brand_id") Long brandId,
                                            @RequestParam("category_id") Long categoryId,
                                            @RequestParam("description") String description,
                                            @RequestParam("variant") String variantDTOs,@RequestParam("specification") String specification, @RequestParam(value = "imagesProduct", required = false)MultipartFile[] imagesProduct) throws IOException {
        String username = authentication.getName();
        try {
            String thumbImage = "";
            if(imageThumb != null){
                thumbImage = uploadFileService.uploadFile(imageThumb);
            }

            List<String> listStringImages= new ArrayList<>();
            if (imagesProduct != null){
                for (MultipartFile file : imagesProduct) {
                    if (!file.isEmpty()) {
                        String  imageUrl = uploadFileService.uploadFile(file);
                        listStringImages.add(imageUrl);
                    }
                }
            }
            Gson gson = new Gson();
            Type listType = new TypeToken<List<VariantDTO>>() {}.getType();
            List<VariantDTO> variantDTOList = gson.fromJson(variantDTOs, listType) ;
//            PhoneSpecificationDTO phoneSpecificationDTO = gson.fromJson(specification, PhoneSpecificationDTO.class);
            ProductRequestDTO productRequestDTO = new ProductRequestDTO(name,brandId,categoryId,thumbImage,listStringImages,description,variantDTOList, specification);
            return new ResponseEntity<>(productService.updateProductById(username,id, productRequestDTO),HttpStatus.OK);
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
        catch (ProductNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (BrandNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(productService.deleteProductById(id),HttpStatus.OK);
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

    @GetMapping("/product/suggest-name")
    public ResponseEntity<?> suggestNameSearch(@RequestParam(value = "pageNo",defaultValue = "0")int pageNo,
                                               @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                               @RequestParam(value = "sortBy",defaultValue = "id")String sortBy,
                                               @RequestParam(value = "sortDir",defaultValue = "asc")String sortDir,@RequestParam("keyName") String keyName){
        try {
            return new ResponseEntity<>(productService.getNameSearch(pageNo, pageSize, sortBy, sortDir,keyName),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/suggest-products")
    public ResponseEntity<?> suggestProductSearch(@RequestParam("keyName") String keyName){
        try {
            return new ResponseEntity<>(productService.suggestProductSearch(keyName),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product/by-ids")
    public ResponseEntity<?> getProductsByIds(@RequestBody List<Long> ids) {
        try {
            return new ResponseEntity<>(productService.getProductsByListId(ids),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
