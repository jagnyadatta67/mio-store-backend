package com.miostore.product.entity;


import com.miostore.common.entity.BaseEntity;
import com.miostore.user.entity.User;
import jakarta.persistence.*;


@Entity
@Table(name = "product_reviews")
public class ProductReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating; // e.g. 1-5 stars

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}