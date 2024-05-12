package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordReset,Long > {
}
