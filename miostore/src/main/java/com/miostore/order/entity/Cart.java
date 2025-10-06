package com.miostore.order.entity;

import com.miostore.common.entity.BaseEntity;
import com.miostore.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One cart belongs to one user
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // A cart has many items
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
}
