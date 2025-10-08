package com.miostore.user.controller;

import com.miostore.user.dto.WellnessProfileRequest;
import com.miostore.user.dto.WellnessProfileResponse;
import com.miostore.user.service.login.WellnessProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class WellnessProfileController {
    private final WellnessProfileService profileService;

    @PostMapping
    public WellnessProfileResponse saveProfile(@RequestBody WellnessProfileRequest request) {
        return profileService.saveOrUpdateProfile(request);
    }

    @GetMapping
    public WellnessProfileResponse getProfile() {
        return profileService.getProfile();
    }
}
