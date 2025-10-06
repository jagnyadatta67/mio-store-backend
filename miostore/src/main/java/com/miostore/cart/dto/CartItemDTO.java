package com.miostore.cart.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Cart item details")
public class CartItemDTO {

    @Schema(description = "Product ID", example = "101")
    private Long productId;

    @Schema(description = "Product name", example = "Organic Foxtail Millet")
    private String productName;

    @Schema(description = "Product image URL", example = "https://www.themiostore.com/images/prod-foxtail.png")
    private String imageUrl;

    @Schema(description = "Quantity added to cart", example = "2")
    private Integer quantity;

    @Schema(description = "Unit price of the product", example = "120.0")
    private Double price;

    @Schema(description = "Variant name (e.g., 1kg, 500g, etc.)", example = "1kg")
    private String variant;
}
