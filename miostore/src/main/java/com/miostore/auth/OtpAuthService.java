package com.miostore.auth;

import com.miostore.user.dto.*;
import com.miostore.user.entity.*;
import com.miostore.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OtpAuthService {
    private final UserRepository userRepository;
    private final UserOtpRepository otpRepository;
    private final JwtService jwtService;

    // 1️⃣ Generate OTP
    public String generateOtp(String identifier) {
        //String otp = String.format("%06d", new Random().nextInt(999999));
        String otp="123456";
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        UserOtp otpEntity = UserOtp.builder()
                .identifier(identifier)
                .otpCode(otp)
                .expiresAt(expiry)
                .used(false)
                .build();

        otpRepository.save(otpEntity);

        // TODO: integrate SMS or Email service here
        System.out.println("✅ OTP for " + identifier + " is: " + otp);
        return otp;
    }

    public String verifyOtp(OtpVerifyDTO request) {
        Optional<UserOtp> otpOptional =
                otpRepository.findTopByIdentifierAndUsedFalseOrderByExpiresAtDesc(request.getIdentifier());

        if (otpOptional.isEmpty()) return null;

        UserOtp otp = otpOptional.get();
        if (otp.isExpired() || !otp.getOtpCode().equals(request.getOtp())) return null;

        otp.setUsed(true);
        otpRepository.save(otp);

        // ✅ Create or fetch user
        User user = createOrVerifyUser(request.getIdentifier());
        // ✅ Generate JWT safely
        Map<String, Object> claims = new HashMap<>();
        if (user.getEmail() != null) claims.put("email", user.getEmail());
        if (user.getPhone() != null) claims.put("phone", user.getPhone());
        claims.put("name", user.getName());

        String token = jwtService.generateToken(
                String.valueOf(user.getId()),
                claims
        );

        return token;
    }

    private User createOrVerifyUser(String identifier) {
        Optional<User> userOptional = userRepository.findByEmailOrPhone(identifier);
        User user = userOptional.orElse(
                User.builder()
                        .name("User-" + identifier)
                        .email(identifier.contains("@") ? identifier : null)
                        .phone(!identifier.contains("@") ? identifier : null)
                        .verified(true)
                        .build()
        );
        user.setVerified(true);
        return userRepository.save(user);
    }
}