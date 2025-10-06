package com.miostore.order.entity;

import com.miostore.common.entity.BaseEntity;

import com.miostore.product.entity.Product;
import com.miostore.product.entity.ProductVariant;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(
        name = "cart_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "variant_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each CartItem belongs to a Cart
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant product;

    private Integer quantity;
    private Double price;


}
