package com.miostore.user.entity;

import com.miostore.common.entity.BaseEntity;
import com.miostore.order.entity.Cart;
import com.miostore.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"orders", "cart"})
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private Boolean verified = false;

    // For password-based admins/support users only
    private String passwordHash;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Cart cart;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String profilePicture;
    private LocalDateTime lastLoginAt;
}
