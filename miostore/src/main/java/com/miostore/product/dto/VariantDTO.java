package com.miostore.product.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantDTO {
    private String sku;
    private String unitLabel;
    private Double price;
    private Double salePrice;
    private Integer quantity;
    private String color;
    private String colorHex;
    private List<String> variantImageUrl;
    private List<String> variantThumbImageUrl;

}
