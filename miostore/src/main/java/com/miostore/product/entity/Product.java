package com.miostore.product.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.miostore.common.entity.BaseEntity;
import com.miostore.offer.entity.Offer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"categories", "variants", "offers"})
@EqualsAndHashCode(exclude = {"categories", "variants", "offers"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String brand;
    private Double rating;
    private String currency;
    private String sku;
    private String ean;
    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductVariant> variants = new HashSet<>();
    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Offer> offers = new HashSet<>();
    // Many-to-many: a product can belong to multiple categories
    @Builder.Default
    @ManyToMany
    @Setter(AccessLevel.NONE)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


}
