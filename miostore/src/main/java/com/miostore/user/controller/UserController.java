package com.miostore.user.controller;

import com.miostore.user.dto.UserProfileDTO;
import com.miostore.user.service.login.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User profile and account management")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get current user profile", description = "Returns details of the logged-in user")
    public ResponseEntity<UserProfileDTO> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }
}
