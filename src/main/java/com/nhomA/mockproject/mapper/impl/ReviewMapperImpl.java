package com.nhomA.mockproject.mapper.impl;

import com.nhomA.mockproject.dto.ReviewRequestDTO;
import com.nhomA.mockproject.dto.ReviewResponseDTO;
import com.nhomA.mockproject.entity.Product;
import com.nhomA.mockproject.entity.Reviews;
import com.nhomA.mockproject.mapper.ReviewMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapperImpl implements ReviewMapper {
    @Override
    public ReviewResponseDTO toDTO(Reviews reviews) {
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
        reviewResponseDTO.setId(reviews.getId());
        reviewResponseDTO.setRate(reviews.getRate());
        reviewResponseDTO.setName(reviews.getUser().getIdentification().getFullName());
        reviewResponseDTO.setUsername(reviews.getUser().getUsername());
        reviewResponseDTO.setContentReviews(reviews.getContentReviews());
        reviewResponseDTO.setUrlImage(reviews.getUrlImage());
        reviewResponseDTO.setCreatedDate(reviews.getCreatedDate());
        return reviewResponseDTO;
    }

    @Override
    public List<ReviewResponseDTO> toDTOs(List<Reviews> reviewsList) {
        List<ReviewResponseDTO> reviewResponseDTOS = new ArrayList<>();
        for(Reviews rv: reviewsList){
            reviewResponseDTOS.add(this.toDTO(rv));
        }
        return reviewResponseDTOS;
    }
}
