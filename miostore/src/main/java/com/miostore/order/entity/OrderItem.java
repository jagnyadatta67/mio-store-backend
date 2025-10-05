package com.miostore.order.entity;


import com.miostore.product.entity.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key in "order_items" → "order_id"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Foreign key in "order_items" → "product_id"
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double price; // Price per unit at the time of order
}
