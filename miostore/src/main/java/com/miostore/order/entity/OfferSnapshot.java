package com.miostore.order.entity;

import com.miostore.common.entity.BaseEntity;
import com.miostore.offer.entity.DiscountType;
import com.miostore.offer.entity.OfferType;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "offer_snapshots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferSnapshot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String offerCode;
    private String description;
    private Double discountValue;

    @Enumerated(EnumType.STRING)
    private OfferType offerType; // ✅ Enum below (PERCENTAGE/FLAT)
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private boolean active;
    private boolean coupon;
    private boolean couponCode;

}