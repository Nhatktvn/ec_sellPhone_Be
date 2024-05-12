package com.nhomA.mockproject.service.impl;

import com.nhomA.mockproject.dto.ReviewRequestDTO;
import com.nhomA.mockproject.dto.ReviewResponseDTO;
import com.nhomA.mockproject.entity.Product;
import com.nhomA.mockproject.entity.Reviews;
import com.nhomA.mockproject.entity.User;
import com.nhomA.mockproject.exception.ProductNotFoundException;
import com.nhomA.mockproject.exception.ReviewNotFoundException;
import com.nhomA.mockproject.exception.UserNotFoundException;
import com.nhomA.mockproject.mapper.ReviewMapper;
import com.nhomA.mockproject.repository.ProductRepository;
import com.nhomA.mockproject.repository.ReviewsRepository;
import com.nhomA.mockproject.repository.UserRepository;
import com.nhomA.mockproject.service.ReviewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private final UserRepository userRepository;
    private final ReviewsRepository reviewsRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;

    public ReviewsServiceImpl(UserRepository userRepository, ReviewsRepository reviewsRepository, ProductRepository productRepository, ReviewMapper reviewMapper) {
        this.userRepository = userRepository;
        this.reviewsRepository = reviewsRepository;
        this.productRepository = productRepository;
        this.reviewMapper = reviewMapper;
    }

    @Transactional
    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        Optional<User> existedUser = userRepository.findByUsername(reviewRequestDTO.getUsername());
        Optional<Product> existedProduct = productRepository.findById(reviewRequestDTO.getIdProduct());
        if(existedUser.isEmpty()){
            throw new UserNotFoundException("User not found!");
        }
        if(existedProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found!");
        }
        Reviews reviews = new Reviews();
        if(reviewRequestDTO.getUrlImage() != ""){
            reviews.setUrlImage(reviewRequestDTO.getUrlImage());
        }
        reviews.setProduct(existedProduct.get());
        reviews.setRate(reviewRequestDTO.getRate());
        reviews.setUser(existedUser.get());
        reviews.setContentReviews(reviewRequestDTO.getContentReviews());
        reviews.setCreatedDate(ZonedDateTime.now());
        reviewsRepository.save(reviews);
        return reviewMapper.toDTO(reviews);
    }
    @Transactional
    @Override
    public ReviewResponseDTO updateReview(String username, Long idReview, ReviewRequestDTO reviewRequestDTO) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        Optional<Reviews> existedReview = reviewsRepository.findByIdAndUserId(idReview, existedUser.get().getId());
        if(existedReview.isEmpty()){
            throw new ReviewNotFoundException("Review not found!");
        }
        Reviews review = existedReview.get();
        review.setContentReviews(reviewRequestDTO.getContentReviews());
        review.setRate(reviewRequestDTO.getRate());
        if(reviewRequestDTO.getUrlImage() != ""){
            review.setUrlImage(reviewRequestDTO.getUrlImage());
        }
        reviewsRepository.save(review);
        return reviewMapper.toDTO(review);
    }

    @Transactional
    @Override
    public boolean deleteReview(String username, Long idReview) {
        Optional<User> existedUser = userRepository.findByUsername(username);
        Optional<Reviews> existedReview = reviewsRepository.findByIdAndUserId(idReview, existedUser.get().getId());
        if(existedReview.isEmpty()){
            throw new ReviewNotFoundException("Review not found!");
        }
        Reviews review = existedReview.get();
        reviewsRepository.delete(review);
        return true;
    }

    @Transactional
    @Override
    public List<ReviewResponseDTO> getAllReviewsByIdProduct(Long idProduct) {
        Optional<Product> exitedProduct = productRepository.findById(idProduct);
        if(exitedProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found!");
        }
        List<Reviews> reviews = exitedProduct.get().getReviews();
        return reviewMapper.toDTOs(reviews);
    }
}
