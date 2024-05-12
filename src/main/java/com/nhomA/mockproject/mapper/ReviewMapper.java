package com.nhomA.mockproject.mapper;

import com.nhomA.mockproject.dto.ReviewResponseDTO;
import com.nhomA.mockproject.entity.Reviews;

import java.util.List;

public interface ReviewMapper {
    ReviewResponseDTO toDTO (Reviews reviews);
    List<ReviewResponseDTO> toDTOs (List<Reviews> reviewsList);
}
