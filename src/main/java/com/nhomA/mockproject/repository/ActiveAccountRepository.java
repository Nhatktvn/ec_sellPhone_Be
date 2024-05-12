package com.nhomA.mockproject.repository;

import com.nhomA.mockproject.entity.ActiveAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveAccountRepository extends JpaRepository<ActiveAccount, Long> {
}
