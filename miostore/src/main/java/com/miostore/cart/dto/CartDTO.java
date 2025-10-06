package com.miostore.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Cart response object")
public class CartDTO {

    @Schema(description = "Cart ID", example = "10")
    private Long id;

    @Schema(description = "User identifier (email or phone)", example = "9876543210")
    private String userIdentifier;

    @Schema(description = "List of items in the cart")
    private List<CartItemDTO> items;

    @Schema(description = "Total amount of all cart items", example = "450.00")
    private Double totalAmount;
}
