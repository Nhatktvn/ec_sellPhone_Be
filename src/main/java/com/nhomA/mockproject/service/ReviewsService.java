package com.nhomA.mockproject.service;

import com.nhomA.mockproject.dto.ReviewRequestDTO;
import com.nhomA.mockproject.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewsService {
    ReviewResponseDTO createReview (ReviewRequestDTO reviewRequestDTO);
    ReviewResponseDTO updateReview (String username, Long idReview, ReviewRequestDTO reviewRequestDTO);
    boolean deleteReview (String username,Long idReview);
    List<ReviewResponseDTO> getAllReviewsByIdProduct (Long productId);
}
