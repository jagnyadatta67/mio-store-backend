package com.miostore.config;

import com.miostore.auth.JwtService;
import com.miostore.auth.SessionService;
import com.miostore.user.entity.User;
import com.miostore.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final SessionService sessionService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        String userId;

        try {
            userId = jwtService.extractSubject(token);
        } catch (Exception e) {
            throw new ServletException("Invalid JWT token");
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findById(Long.parseLong(userId)).orElse(null);
            if (user != null && jwtService.isTokenValid(token, user)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                // ✅ Store in SessionService
                sessionService.setCurrentUser(user);
            } else {
                throw new ServletException("Invalid or expired JWT");
            }
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            sessionService.clear(); // ✅ important for safety
        }
    }
}

