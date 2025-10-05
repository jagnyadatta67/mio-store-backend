package com.miostore.user;

import com.miostore.user.dto.OtpRequestDTO;
import com.miostore.user.dto.OtpVerifyDTO;
import com.miostore.auth.OtpAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User login/registration via OTP")
public class LoginController {

    private final OtpAuthService otpService;

    @PostMapping("/send-otp")
    @Operation(summary = "Send OTP to email or phone")
    public String sendOtp(@RequestBody OtpRequestDTO request) {
        return otpService.generateOtp(request.getIdentifier());
    }

    @PostMapping("/verify-otp")
    @Operation(summary = "Verify OTP and return JWT token")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerifyDTO request) {
        String token = otpService.verifyOtp(request);
        if (token == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid or expired OTP"));
        }
        return ResponseEntity.ok(Map.of(
                "message", "OTP verified successfully",
                "token", token
        ));
    }
    @GetMapping("/oauth2/success")
    public ResponseEntity<?> oauth2Success(OAuth2AuthenticationToken authentication) {
        var userDetails = authentication.getPrincipal().getAttributes();
        // Extract email/name and create user entry if needed
        return ResponseEntity.ok(Map.of("message", "Google login successful", "user", userDetails));
    }

}