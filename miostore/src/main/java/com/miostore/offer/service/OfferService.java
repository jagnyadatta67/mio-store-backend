package com.miostore.offer.service;

import com.miostore.offer.entity.Offer;

import com.miostore.offer.entity.OfferType;
import com.miostore.offer.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public List<Offer> getActiveProductOffers() {
        return offerRepository.findByOfferTypeAndActiveTrueAndValidTillAfter(OfferType.PRODUCT, LocalDate.now());
    }

    public List<Offer> getActiveCategoryOffers() {
        return offerRepository.findByOfferTypeAndActiveTrueAndValidTillAfter(OfferType.CATEGORY, LocalDate.now());
    }

    public List<Offer> getActiveCartOffers() {
        return offerRepository.findByOfferTypeAndActiveTrueAndValidTillAfter(OfferType.CART, LocalDate.now());
    }

    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer updateOffer(Long id, Offer updated) {
        Offer existing = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDiscountPercent(updated.getDiscountPercent());
        existing.setDiscountAmount(updated.getDiscountAmount());
        existing.setValidFrom(updated.getValidFrom());
        existing.setValidTill(updated.getValidTill());
        existing.setActive(updated.getActive());
        existing.setOfferType(updated.getOfferType());
        return offerRepository.save(existing);
    }
}
