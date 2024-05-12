package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Optional<Favourite> findByUserIdAndProductId (Long userId, Long productId);
}
