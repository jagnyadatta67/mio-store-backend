package com.miostore.order.util;


import com.miostore.offer.entity.Offer;

import com.miostore.order.entity.OfferSnapshot;
import org.springframework.stereotype.Component;

@Component
public class OfferCloneUtil {

    public static OfferSnapshot cloneFromOffer(OfferSnapshot offer) {
        if (offer == null) return null;

        return OfferSnapshot.builder()
                .id(offer.getId())
                .offerCode(offer.getOfferCode())
                .description(offer.getDescription())
                .discountValue(offer.getDiscountValue())
                .discountType(offer.getDiscountType()).offerType(offer.getOfferType())
                .build();
    }
}
