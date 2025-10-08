package com.miostore.order.entity;

import com.miostore.common.entity.BaseEntity;
import com.miostore.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Order extends AbstractOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    // One order has multiple order items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();



    private String status; // PENDING, PAID, SHIPPED, DELIVERED, CANCELLED

    private String razorpayOrderId; // For Razorpay integration
}

