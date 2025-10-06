package com.miostore.offer.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.miostore.product.entity.Category;
import com.miostore.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(exclude = {"categories", "variants", "offers"})
@EqualsAndHashCode(exclude = {"categories", "variants", "offers"})

@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;          // e.g. "Festival Offer"
    private String description;    // e.g. "10% off on millets"

    @Enumerated(EnumType.STRING)
    private OfferType offerType;   // PRODUCT, CATEGORY, or CART

    private Double discountPercent; // e.g. 10
    private Double discountAmount;  // optional, e.g. ₹100 off

    private LocalDate validFrom;
    private LocalDate validTill;
    private Boolean active;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category; // for CATEGORY offers

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product; // for PRODUCT offers

    // Optional: cart-level min spend condition
    private Double minCartValue; // e.g. 999 for ₹100 off
}
