package com.miostore.user.repository;

import com.miostore.user.entity.WellnessProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WellnessProfileRepository extends JpaRepository<WellnessProfile, Long> {
    Optional<WellnessProfile> findByUserId(Long userId);
}
