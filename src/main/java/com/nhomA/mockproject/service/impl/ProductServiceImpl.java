package com.nhomA.mockproject.service.impl;

import com.google.gson.Gson;
import com.nhomA.mockproject.dto.*;
import com.nhomA.mockproject.entity.*;
import com.nhomA.mockproject.exception.BrandNotFoundException;
import com.nhomA.mockproject.exception.ProductNotFoundException;
import com.nhomA.mockproject.mapper.LaptopSpecificationMapper;
import com.nhomA.mockproject.mapper.ProductMapper;
import com.nhomA.mockproject.mapper.PhoneSpecificationMapper;
import com.nhomA.mockproject.mapper.VariantMapper;
import com.nhomA.mockproject.repository.*;
import com.nhomA.mockproject.service.ProductService;
import com.nhomA.mockproject.util.PaginationAndSortingUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final VariantMapper variantMapper;
    private  final BrandRepository brandRepository;
    private final PhoneSpecificationMapper specificationMapper;
    private final PhoneSpecificationRepository phoneSpecificationRepository;
    private final LaptopSpecificationMapper laptopSpecificationMapper;
    private final LaptopSpecificationRepository laptopSpecificationRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper, VariantMapper variantMapper, BrandRepository brandRepository, PhoneSpecificationMapper specificationMapper, PhoneSpecificationRepository phoneSpecificationRepository, LaptopSpecificationMapper laptopSpecificationMapper, LaptopSpecificationRepository laptopSpecificationRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
        this.variantMapper = variantMapper;
        this.brandRepository = brandRepository;
        this.specificationMapper = specificationMapper;
        this.phoneSpecificationRepository = phoneSpecificationRepository;
        this.laptopSpecificationMapper = laptopSpecificationMapper;
        this.laptopSpecificationRepository = laptopSpecificationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public ProductResponseDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductResponseDTO productResponseDTO = productMapper.toResponseDTO(product.get());
        return productResponseDTO;
    }

    @Transactional
    @Override
    public List<ProductResponseDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = productMapper.toResponseDTOs(products);
        return productResponseDTOS;
    }

    @Transactional
    @Override
    public ListProductPageDTO getProductPage(int pageNo, int pageSize, String sortBy, String sortDir, Long idCategory) {
        Pageable pageable= PaginationAndSortingUtils.getPageable(pageNo,pageSize,sortBy,sortDir);
        if (idCategory == null){
            List<Product> productsAll = productRepository.findAll();
            int pageSizes = (int) Math.ceil((double) productsAll.size() /pageSize);
            Page<Product> products= productRepository.findAll(pageable);
            List<Product> productsContent = products.getContent();
            return new ListProductPageDTO(productMapper.toResponseDTOs(productsContent),pageSizes);
        }
        List<Product> productsByCategory = productRepository.findByBrandId(idCategory);
        int pageSizes = (int) Math.ceil((double) productsByCategory.size() /pageSize);
        Page<Product> productPage= productRepository.findByBrandId(idCategory,pageable);
        List<Product> productsContent = productPage.getContent();
        return new ListProductPageDTO(productMapper.toResponseDTOs(productsContent),pageSizes);
    }
    @Transactional
    @Override
    public ProductResponseDTO createProduct(String username, ProductRequestDTO productRequestDTO) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        User user = existedUser.get();
        Product product = productMapper.toEntity(productRequestDTO);
        Optional<Brand> existedBrand = brandRepository.findById(productRequestDTO.getBrand_id());
        if(existedBrand.isEmpty()){
            throw new BrandNotFoundException("Brand not found!");
        }
        Optional<Category> existedCategory = categoryRepository.findById(productRequestDTO.getCategory_id());
        if(existedCategory.isEmpty()){
            throw new BrandNotFoundException("Category not found!");
        }
        List<Variant> variants = variantMapper.toListEntity(productRequestDTO.getVariantDTOList());
        for (Variant variant : variants){
            variant.setProduct(product);
        }
        List<ImagesProduct> imagesProducts = new ArrayList<>();
        for(String urlImage :productRequestDTO.getImagesProduct()){
            ImagesProduct imagesProduct = new ImagesProduct();
            imagesProduct.setUrlImage(urlImage);
            imagesProduct.setProduct(product);
            imagesProducts.add(imagesProduct);
        }
        saveSpecification(productRequestDTO.getCategory_id(),product, productRequestDTO.getSpecification());
//        Gson gson = new Gson();
//        LaptopSpecificationDTO laptopSpecificationDTO = gson.fromJson(productRequestDTO.getSpecification(), LaptopSpecificationDTO.class);
//        LaptopSpecification laptopSpecification = laptopSpecificationMapper.toEntity(laptopSpecificationDTO);
//        laptopSpecification.setProduct(product);
//        laptopSpecificationRepository.save(laptopSpecification);
//        product.setSpecification(phoneSpecification);
        product.setImagesProduct(imagesProducts);
        product.setVariants(variants);
        Brand brand = existedBrand.get();
        brand.getProducts().add(product);
        product.setBrand(brand);
        product.setCategory(existedCategory.get());
        product.setUserCreated(user);
        product.setUserUpdated(user);
        product.setCreatedDate(ZonedDateTime.now());
        product.setUpdatedDate(ZonedDateTime.now());
        Product saveProduct = productRepository.save(product);
        return null;
    }

    @Transactional
    @Override
    public List<ProductResponseDTO> createProductByFile(String username, List<ProductRequestDTO> productRequestDTOs) {
        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for(ProductRequestDTO productRequestDTO : productRequestDTOs){
            productResponseDTOs.add(createProduct(username,productRequestDTO));
        }
        return productResponseDTOs;
    }

    @Transactional
    @Override
    public ProductResponseDTO updateProductById(String username, Long id, ProductRequestDTO productRequestDTO){
        User userUpdated = userRepository.findByUsername(username).get();
        Optional<Product> existedProduct = productRepository.findById(id);
        if(existedProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found!");
        }
        Optional<Brand> existedCategory = brandRepository.findById(productRequestDTO.getBrand_id());
        if(existedProduct.isEmpty()){
            throw new BrandNotFoundException("Category not found!");
        }
        Product product = existedProduct.get();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        if(productRequestDTO.getImageThumb() != ""){
            product.setImageThumb(productRequestDTO.getImageThumb());
        }
        if(productRequestDTO.getImagesProduct().size() >0){
            List<ImagesProduct> imagesProducts = new ArrayList<>();
            for(String urlImage :productRequestDTO.getImagesProduct()){
                ImagesProduct imagesProduct = new ImagesProduct();
                imagesProduct.setUrlImage(urlImage);
                imagesProduct.setProduct(product);
                imagesProducts.add(imagesProduct);
            }
            product.setImagesProduct(imagesProducts);
        }
        Brand brand = existedCategory.get();
        product.setBrand(brand);
        product.setUserUpdated(userUpdated);
        product.setUpdatedDate(ZonedDateTime.now());
        Product saveProduct = productRepository.save(product);
        return productMapper.toResponseDTO(saveProduct);
    }
    @Transactional
    @Override
    public Boolean deleteProductById(Long id) {
        laptopSpecificationRepository.deleteByProductId(id);
        phoneSpecificationRepository.deleteByProductId(id);
        productRepository.deleteById(id);
        return true;
    }
    @Transactional
    @Override
    public List<ProductResponseDTO> getProductsByCategory(String categoryName) {
        Optional<Brand> existedCategory = brandRepository.findByName(categoryName);
        if(existedCategory.isEmpty()){
            throw new BrandNotFoundException("Category not found!");
        }
        Brand brand = existedCategory.get();
        return productMapper.toResponseDTOs(brand.getProducts());
    }

    @Transactional
    @Override
    public List<ProductResponseDTO> searchProduct(String searchName) {
        List<Product> emptyProducts = productRepository.findByNameContainingIgnoreCase(searchName);
        if(emptyProducts.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        List<ProductResponseDTO> productResponseDTOS = productMapper.toResponseDTOs(emptyProducts);
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO getProductByNameExact(String name) {
        Optional<Product> existedProduct = productRepository.findByNameExact(name);
        if(existedProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found!");
        }
        ProductResponseDTO productResponseDTO = productMapper.toResponseDTO(existedProduct.get());
        return productResponseDTO;
    }

    @Override
    public List<String> getNameSearch(int pageNo, int pageSize, String sortBy, String sortDir, String keyName) {
        if (keyName == ""){
            return new ArrayList<>();
        }
        List<Product> products = productRepository.findTop5ByNameContainingIgnoreCase(keyName);
        if (products.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        List<String> namesSearch = new ArrayList<>();
        for (Product p : products){
            namesSearch.add(p.getName());
        }
        return namesSearch;
    }

    @Override
    public List<ProductResponseDTO> suggestProductSearch(String keyName) {
        if (keyName == ""){
            return new ArrayList<>();
        }
        List<Product> products = productRepository.findTop5ByNameContainingIgnoreCase(keyName);
        if (products.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return productMapper.toResponseDTOs(products);
    }

    public void saveSpecification (Long category_id, Product product, String specificationString) {
        Gson gson = new Gson();
        Optional<Category> category = categoryRepository.findById(category_id);
//        SpecificationDTO specificationDTO = gson.fromJson(productRequestDTO.getSpecification(), SpecificationDTO.class);
        switch (category.get().getName().toLowerCase()) {
            case "điện thoại":
                PhoneSpecificationDTO phoneSpecificationDTO = gson.fromJson(specificationString, PhoneSpecificationDTO.class);
                PhoneSpecification phoneSpecification = specificationMapper.toEntity(phoneSpecificationDTO);
                phoneSpecification.setProduct(product);
                phoneSpecificationRepository.save(phoneSpecification);
                return;
            case "laptop":
                LaptopSpecificationDTO laptopSpecificationDTO = gson.fromJson(specificationString, LaptopSpecificationDTO.class);
                LaptopSpecification laptopSpecification = laptopSpecificationMapper.toEntity(laptopSpecificationDTO);
                laptopSpecification.setProduct(product);
                laptopSpecificationRepository.save(laptopSpecification);
                return;
            default:
                return;
        }
    }

//    public void deleteSpecification (Long category_id, Product product) {
//        Gson gson = new Gson();
//        Optional<Category> category = categoryRepository.findById(category_id);
////        SpecificationDTO specificationDTO = gson.fromJson(productRequestDTO.getSpecification(), SpecificationDTO.class);
//        switch (category.get().getName().toLowerCase()) {
//            case "điện thoại":
//                PhoneSpecificationDTO phoneSpecificationDTO = gson.fromJson(specificationString, PhoneSpecificationDTO.class);
//                PhoneSpecification phoneSpecification = specificationMapper.toEntity(phoneSpecificationDTO);
//                phoneSpecification.setProduct(product);
//                phoneSpecificationRepository.save(phoneSpecification);
//                return;
//            case "laptop":
//                LaptopSpecificationDTO laptopSpecificationDTO = gson.fromJson(specificationString, LaptopSpecificationDTO.class);
//                LaptopSpecification laptopSpecification = laptopSpecificationMapper.toEntity(laptopSpecificationDTO);
//                laptopSpecification.setProduct(product);
//                laptopSpecificationRepository.save(laptopSpecification);
//                return;
//            default:
//                return;
//        }
//    }
}
