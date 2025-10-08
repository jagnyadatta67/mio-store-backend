package com.miostore.order.entity;

import com.miostore.common.entity.BaseEntity;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OfferSnapshot  {
    private String offerCode;
    private String description;
    private Double discountValue;
    private String discountType; // e.g., PERCENTAGE or FLAT
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}