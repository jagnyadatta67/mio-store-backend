package com.miostore.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product_variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku; // e.g. "MIL-FT-001-1KG", "MIL-FT-001-500GM"
    private String ean;

    private String unitLabel; // e.g. "1kg", "500gm", "1 piece", "3 piece"
    private String color;
    private Integer quantity; // stock

    private Double price;

    private Double salePrice; // optional if discounted
    private List<String> imageUrl;
    private List<String> thumbImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
