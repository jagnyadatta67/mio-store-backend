package com.miostore.address.entity;



import com.miostore.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private boolean isDefault;
    private boolean isCloned;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private AddressType type; // USER_PROFILE, CART_CLONE, ORDER_CLONE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner; // ✅ The original user who owns/created this address
}
