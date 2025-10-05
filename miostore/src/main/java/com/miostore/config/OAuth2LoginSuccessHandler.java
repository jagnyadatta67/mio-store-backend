package com.miostore.config;


import com.miostore.user.entity.User;
import com.miostore.user.repository.UserRepository;
import com.miostore.auth.JwtService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException, java.io.IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not provided by Google");
            return;
        }

        // ✅ Create or update user
        Optional<User> existing = userRepository.findByEmailOrPhone(email);
        User user = existing.orElseGet(() ->
                userRepository.save(User.builder()
                        .email(email)
                        .name(name != null ? name : "GoogleUser")
                        .verified(true)
                        .build())
        );

        // ✅ Generate JWT token
        String token = jwtService.generateToken(
                user.getId().toString(),
                Map.of("email", user.getEmail(), "name", user.getName())
        );

        // ✅ Return token to client
        response.setContentType("application/json");
        response.getWriter().write("""
                {
                    "message": "Google login successful",
                    "token": "%s",
                    "tokenType": "Bearer"
                }
                """.formatted(token));
    }
}
