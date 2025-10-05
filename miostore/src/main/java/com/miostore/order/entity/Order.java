package com.miostore.order.entity;

import com.miostore.common.entity.BaseEntity;
import com.miostore.user.entity.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key column in "orders" table → "user_id"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One order has multiple order items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    private Double totalAmount;

    private String status; // PENDING, PAID, SHIPPED, DELIVERED, CANCELLED

    private String razorpayOrderId; // For Razorpay integration
}

