package com.miostore.user.dto;


import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class WellnessProfileResponse {
    private Long id;
    private String name;
    private String ageGroup;
    private String gender;
    private String location;
    private String dietType;
    private Boolean prefersOrganic;
    private List<String> healthGoals;
    private List<String> restrictions;
    private String activityLevel;
    private String shoppingFrequency;
    private List<String> interests;
    private String buyingPriority;
    private String feedback;
    private String personaType;
}
