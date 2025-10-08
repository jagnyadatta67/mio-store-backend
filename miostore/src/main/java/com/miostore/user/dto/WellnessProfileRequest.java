package com.miostore.user.dto;


import lombok.Data;
import java.util.List;

@Data
public class WellnessProfileRequest {
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
}
