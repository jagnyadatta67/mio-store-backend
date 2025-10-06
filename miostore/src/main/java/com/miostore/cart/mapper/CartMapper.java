package com.miostore.cart.mapper;


import com.miostore.cart.dto.CartDTO;
import com.miostore.cart.dto.CartItemDTO;
import com.miostore.order.entity.Cart;
import com.miostore.order.entity.CartItem;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CartMapper {

    /**
     * ✅ Converts a Cart entity into a CartDTO.
     */
    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(CartMapper::mapItem)
                .collect(Collectors.toList());

        double total = itemDTOs.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        return CartDTO.builder()
                .id(cart.getId())
                .userIdentifier(cart.getUser() != null
                        ? (cart.getUser().getPhone() != null
                        ? cart.getUser().getPhone()
                        : cart.getUser().getEmail())
                        : null)
                .items(itemDTOs)
                .totalAmount(total)
                .build();
    }

    /**
     * ✅ Converts a CartItem entity into a CartItemDTO.
     */
    private static CartItemDTO mapItem(CartItem item) {
        return CartItemDTO.builder()
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getName())
                .imageUrl(item.getProduct().getThumbImageUrl())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .variant(item.getProduct().getColor())
                .build();
    }
}

