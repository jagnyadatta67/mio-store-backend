package com.miostore.product.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"products", "subcategories", "offers"})
@EqualsAndHashCode(exclude = {"products", "subcategories", "offers"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Category  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String slug;
    private String description;

    // Self-referencing for hierarchy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subcategories = new ArrayList<>();

    // Many-to-many with products
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore

    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Offer> offers = new ArrayList<>();


}
