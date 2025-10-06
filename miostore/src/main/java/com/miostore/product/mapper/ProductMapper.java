package com.miostore.product.mapper;



import com.miostore.offer.entity.Offer;
import com.miostore.product.dto.*;
import com.miostore.product.entity.*;

import java.util.*;
import java.util.stream.Collectors;

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
                .variantImageUrl(
                        Optional.ofNullable(variant.getImageUrl()).orElse(List.of())
                )
                .build();
    }

    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        // Map offers safely
        Set<OfferDTO> offerDTOs = Optional.ofNullable(product.getOffers())
                .orElse(Collections.emptySet())
                .stream()
                .map(ProductMapper::toOfferDTO)
                .collect(Collectors.toSet());

        // Map variants safely
        Set<ProductVariant> variants = Optional.ofNullable(product.getVariants())
                .orElseGet(HashSet::new);

        Set<VariantDTO> variantDTOs = variants.stream()
                .map(ProductMapper::toVariantDTO)
                .collect(Collectors.toCollection(HashSet::new));


        // Map categories (names only)
        Set<Category> categories = Optional.ofNullable(product.getCategories())
                .orElseGet(HashSet::new);

        Set<String> categoryNames = categories.stream()
                .map(Category::getName)
                .collect(Collectors.toCollection(HashSet::new));

        return ProductDTO.builder()
                .id(String.valueOf(product.getId()))
                .description(product.getDescription())
                .currency(product.getCurrency())
                .sku(product.getSku())
                .brand(product.getBrand())
                .category(categoryNames)
                .rating(product.getRating())
                .variants(variantDTOs)
                .offers(offerDTOs)
                .build();
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        return Optional.ofNullable(products)
                .orElse(Collections.emptyList())
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
