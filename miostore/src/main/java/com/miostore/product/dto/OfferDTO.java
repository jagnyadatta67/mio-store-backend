package com.miostore.product.dto;

import com.miostore.offer.entity.OfferType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDTO {
    private Long id;

    private String title;          // e.g. "Festival Offer"
    private String description;    // e.g. "10% off on millets"

    @Enumerated(EnumType.STRING)
    private String offerType;   // PRODUCT, CATEGORY, or CART

    private Double discountPercent; // e.g. 10
    private Double discountAmount;  // optional, e.g. ₹100 off

    private LocalDate validFrom;
    private LocalDate validTill;
    private Boolean active;
    private Double minCartValue;
}
