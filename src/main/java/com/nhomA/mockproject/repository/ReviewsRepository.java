package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    Optional<Reviews> findByIdAndUserId (Long id, Long UserId);
}
