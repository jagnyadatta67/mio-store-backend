package com.miostore.cart.mapper;


import com.miostore.address.OrderAddressMapper;
import com.miostore.address.dto.AddressRequest;
import com.miostore.address.entity.Address;
import com.miostore.cart.dto.CartDTO;
import com.miostore.cart.dto.CartItemDTO;
import com.miostore.order.entity.Cart;
import com.miostore.order.entity.CartItem;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@UtilityClass
public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(CartMapper::mapItem)
                .collect(Collectors.toList());

        double total = itemDTOs.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
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
                .address(OrderAddressMapper.toDTO(cart.getShippingAddress()))
                .build();
    }

    private static CartItemDTO mapItem(CartItem item) {
        return CartItemDTO.builder()
                .productId(item.getProduct().getId())
                .sku(item.getProduct().getSku())
                .productName(item.getProduct().getName())
                .imageUrl(item.getProduct().getProduct().getThumbnailImage())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .variant(item.getProduct().getColor())
                .unitLabel(item.getProduct().getUnitLabel())
                .build();
    }

    private static AddressRequest mapAddress(Address shipping) {
        if (shipping == null) return null;
        return AddressRequest.builder()
                .fullName(shipping.getFullName())
                .phoneNumber(shipping.getPhoneNumber())
                .addressLine1(shipping.getAddressLine1())
                .addressLine2(shipping.getAddressLine2())
                .city(shipping.getCity())
                .state(shipping.getState())
                .postalCode(shipping.getPostalCode())
                .country(shipping.getCountry())
                .isDefault(shipping.isDefault())
                .build();
    }
}
