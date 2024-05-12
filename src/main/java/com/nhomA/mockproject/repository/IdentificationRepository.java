package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.Identification;
import com.nhomA.mockproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdentificationRepository extends JpaRepository<Identification, Long> {
    Optional<Identification> findByEmail (String email);
}
