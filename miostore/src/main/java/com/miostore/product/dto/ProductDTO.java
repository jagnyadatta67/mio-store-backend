

package com.miostore.product.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private String currency;
    private String sku;
    private String brand;
    private Set<String> category;
    private String imageUrl;
    private String availability;
    private Double rating;
    private Set<VariantDTO> variants;
    private Set<OfferDTO> offers;
}
