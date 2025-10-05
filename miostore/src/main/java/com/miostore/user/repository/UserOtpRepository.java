package com.miostore.user.repository;

import com.miostore.user.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {
    Optional<UserOtp> findTopByIdentifierAndUsedFalseOrderByExpiresAtDesc(String identifier);
}