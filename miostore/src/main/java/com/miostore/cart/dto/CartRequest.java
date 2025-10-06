package com.miostore.cart.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request object for adding items to the cart")
public class CartRequest {

    @Schema(
            description = "User identifier (can be email or phone number)",
            example = "9876543210"
    )
    private String identifier;

    @Schema(
            description = "Product ID to add to the cart",
            example = "101"
    )
    private Long productId;

    @Schema(
            description = "Quantity of the product to add",
            example = "2"
    )
    private Integer quantity;

    @Schema(
            description = "Product variant (e.g. 1kg, 500ml, Red, etc.)",
            example = "1kg"
    )
    private String variant;
}
