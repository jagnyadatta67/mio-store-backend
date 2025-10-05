package com.miostore.product.mapper;

import com.miostore.offer.entity.Offer;
import com.miostore.product.dto.*;
import com.miostore.product.entity.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

public class ProductMapper {

    public static OfferDTO toOfferDTO(Offer offer) {
        if (offer == null) return null;
        return OfferDTO.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .discountPercent(offer.getDiscountPercent())
                .validFrom(offer.getValidFrom())
                .validTill(offer.getValidTill())
                .active(offer.getActive())
                .minCartValue(offer.getMinCartValue())
                .build();
    }

    public static VariantDTO toVariantDTO(ProductVariant variant) {
        if (variant == null) return null;
        return VariantDTO.builder()
                .sku(variant.getSku())
                .unitLabel(variant.getUnitLabel())
                .price(variant.getPrice())
                .salePrice(variant.getSalePrice())
                .quantity(variant.getQuantity())
                .color(variant.getColor())
                .variantThumbImageUrl(variant.getThumbImageUrl())
                .variantImageUrl(variant.getImageUrl())
                .build();
    }

    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        // Map offers
        List<OfferDTO> offerDTOs = Optional.ofNullable(product.getOffers())
                .stream()
                .flatMap(List::stream)
                .map(ProductMapper::toOfferDTO)
                .collect(Collectors.toList());

        // Map variants
        List<VariantDTO> variantDTOs = Optional.ofNullable(product.getVariants())
                .stream()
                .flatMap(List::stream)
                .map(ProductMapper::toVariantDTO)
                .collect(Collectors.toList());

        return ProductDTO.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .description(product.getDescription())
                .currency(product.getCurrency())
                .sku(product.getSku())
                .brand(product.getBrand())
                .category(product.getCategories().stream().map(cat->cat.getName()).collect(Collectors.toList()))
                .availability(product.getAvailability())
                .rating(product.getRating())
                .variants(variantDTOs)
                .offers(offerDTOs)
                .build();
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        return Optional.ofNullable(products).orElse(List.of())
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
