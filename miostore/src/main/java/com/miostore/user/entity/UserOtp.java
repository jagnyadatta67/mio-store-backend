package com.miostore.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_otps")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOtp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier; // email or phone
    private String otpCode;

    private LocalDateTime expiresAt;

    private Boolean used = false;

    public boolean isExpired() {
        return expiresAt.isBefore(LocalDateTime.now());
    }
}