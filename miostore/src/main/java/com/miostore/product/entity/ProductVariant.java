package com.miostore.product.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product_variants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "product")
@EqualsAndHashCode(exclude = "product")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String sku; // e.g. "MIL-FT-001-1KG", "MIL-FT-001-500GM"
    private String ean;
    private String unitLabel; // e.g. "1kg", "500gm", "1 piece", "3 piece"
    private String color;
    private Integer quantity; // stock
    private Double price;
    private Double salePrice; // optional if discounted
    @ElementCollection
    @CollectionTable(
            name = "product_variant_images",
            joinColumns = @JoinColumn(name = "variant_sku", referencedColumnName = "sku")
    )
    @Column(name = "image_url")
    private List<String> imageUrl;
    private String thumbImageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;
}
