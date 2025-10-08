package com.miostore.user.service.login;



import com.miostore.auth.SessionService;
import com.miostore.user.dto.WellnessProfileRequest;
import com.miostore.user.dto.WellnessProfileResponse;
import com.miostore.user.entity.User;
import com.miostore.user.entity.WellnessProfile;
import com.miostore.user.repository.UserRepository;
import com.miostore.user.repository.WellnessProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class WellnessProfileService {
    private final WellnessProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final UserService userService; // 🧩 assumes you have a service that fetches logged-in user

    @Transactional
    public WellnessProfileResponse saveOrUpdateProfile(WellnessProfileRequest request) {
        User user = sessionService.getCurrentUser(); // ✅ Fetch logged-in user (from JWT or session)

        // Check if profile already exists
        WellnessProfile profile = profileRepository.findByUserId(user.getId())
                .orElse(new WellnessProfile());
        profile.setUser(user);

        // Update fields
        profile.setName(request.getName());
        profile.setAgeGroup(request.getAgeGroup());
        profile.setGender(request.getGender());
        profile.setLocation(request.getLocation());
        profile.setDietType(request.getDietType());
        profile.setPrefersOrganic(request.getPrefersOrganic());
        profile.setHealthGoals(request.getHealthGoals());
        profile.setRestrictions(request.getRestrictions());
        profile.setActivityLevel(request.getActivityLevel());
        profile.setShoppingFrequency(request.getShoppingFrequency());
        profile.setInterests(request.getInterests());
        profile.setBuyingPriority(request.getBuyingPriority());
        profile.setFeedback(request.getFeedback());

        // Compute persona type 💡
        String personaType = determinePersonaType(request);
        profile.setPersonaType(personaType);

        WellnessProfile saved = profileRepository.save(profile);
        user.setName(request.getName());
        userRepository.save(user);
        return mapToResponse(saved);
    }


    @Transactional(readOnly = true)
    public WellnessProfileResponse getProfile() {
        User user = sessionService.getCurrentUser();
        WellnessProfile profile = profileRepository.findByUserId(user.getId())
                .orElse(null);

        if (profile == null) {
            return null; // or throw exception if needed
        }

        return mapToResponse(profile);
    }

    // 💡 Simple rule-based persona generator
    private String determinePersonaType(WellnessProfileRequest req) {
        if (req.getHealthGoals() == null) return "Balanced Explorer";

        if (req.getHealthGoals().contains("Weight Loss") && req.getPrefersOrganic() != null && req.getPrefersOrganic())
            return "Organic Weight Balancer";

        if (req.getHealthGoals().contains("More Energy"))
            return "Vitality Seeker";

        if (req.getHealthGoals().contains("Better Digestion"))
            return "Mindful Nourisher";

        if (req.getDietType() != null && req.getDietType().equalsIgnoreCase("Vegan"))
            return "Eco Wellness Advocate";

        return "Holistic Health Explorer";
    }

    // 🧾 Mapper
    private WellnessProfileResponse mapToResponse(WellnessProfile p) {
        return WellnessProfileResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .ageGroup(p.getAgeGroup())
                .gender(p.getGender())
                .location(p.getLocation())
                .dietType(p.getDietType())
                .prefersOrganic(p.getPrefersOrganic())
                .healthGoals(p.getHealthGoals())
                .restrictions(p.getRestrictions())
                .activityLevel(p.getActivityLevel())
                .shoppingFrequency(p.getShoppingFrequency())
                .interests(p.getInterests())
                .buyingPriority(p.getBuyingPriority())
                .feedback(p.getFeedback())
                .personaType(p.getPersonaType())
                .build();
    }
}
