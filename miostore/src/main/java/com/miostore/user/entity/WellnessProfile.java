package com.miostore.user.entity;


import com.miostore.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "wellness_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WellnessProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Link with user
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // 👤 Basic Info
    private String name;
    private String ageGroup;      // 18–25, 26–35, etc.
    private String gender;        // optional
    private String location;      // city, region

    // 🍽️ Food & Diet
    private String dietType;      // Vegetarian, Vegan, etc.
    private Boolean prefersOrganic; // true = organic-only

    // ⚕️ Health Goals
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wellness_health_goals", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "goal")
    private List<String> healthGoals; // ["Weight Loss", "Better Digestion", ...]

    // 🚫 Restrictions
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wellness_restrictions", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "restriction")
    private List<String> restrictions; // ["Gluten-free", "Nut allergy", ...]

    // 🏃 Lifestyle
    private String activityLevel; // Sedentary, Moderate, Active, etc.

    // 🛒 Shopping Preferences
    private String shoppingFrequency;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wellness_interests", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "interest")
    private List<String> interests; // ["Millets & Grains", "Organic Snacks", ...]

    private String buyingPriority; // e.g., "Quality", "Local Sourcing"

    // 💬 User feedback
    @Column(length = 500)
    private String feedback;

    // 🕓 Meta Info
    private String personaType; // computed (e.g., "Mindful Nourisher")
}
