package com.miostore.offer.controller;

import com.miostore.offer.entity.Offer;
import com.miostore.offer.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
@Tag(name = "Offers", description = "Manage product, category, and cart offers")
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/products")
    @Operation(summary = "Get all active product-level offers")
    public List<Offer> getProductOffers() {
        return offerService.getActiveProductOffers();
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all active category-level offers")
    public List<Offer> getCategoryOffers() {
        return offerService.getActiveCategoryOffers();
    }

    @GetMapping("/cart")
    @Operation(summary = "Get all active cart-level offers")
    public List<Offer> getCartOffers() {
        return offerService.getActiveCartOffers();
    }

    @PostMapping
    @Operation(summary = "Create a new offer")
    public Offer createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }
}
