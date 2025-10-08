
package com.miostore.order.mapper;


import com.miostore.address.OrderAddressMapper;
import com.miostore.order.entity.CartItem;
import com.miostore.order.dto.OrderDTO;
import com.miostore.order.dto.OrderItemDTO;
import com.miostore.order.entity.Cart;
import com.miostore.order.entity.Order;
import com.miostore.order.entity.OrderItem;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {

    /**
     * 🧩 Converts a Cart → Order
     */
    public static Order mapCartToOrder(Cart cart, String paymentMethod, double deliveryFee) {
        double subtotal = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        double total = subtotal + deliveryFee;

        List<OrderItem> orderItems = mapToOrderItems(cart.getItems());

        Order order = Order.builder()
                .code(generateOrderCode())
                .user(cart.getUser())
                .shippingAddress(cart.getShippingAddress()) // snapshot from cart
                .appliedOffer(cart.getAppliedOffer()) // snapshot of offer if any
                .items(orderItems)
                .subTotal(subtotal)
                .shippingCost(deliveryFee)
                .totalAmount(total)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentMethod.equalsIgnoreCase("COD") ? "Pending" : "Paid")
                .orderStatus("Confirmed")
                .createdAt(LocalDateTime.now())
                .build();

        // Set reverse relationship
        orderItems.forEach(i -> i.setOrder(order));

        return order;
    }

    /**
     * 🧩 Converts CartItems → OrderItems
     */
    public static List<OrderItem> mapToOrderItems(List<CartItem> items) {
        return items.stream()
                .map(i -> OrderItem.builder()
                        .variant(i.getProduct())
                        .price(i.getPrice())
                        .quantity(i.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 🧩 Converts an Order entity → OrderDTO
     */
    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .orderNumber(order.getCode())
                .orderStatus(order.getOrderStatus())
                .subtotal(order.getSubTotal())
                .totalAmount(order.getTotalAmount())
                .deliveryFee(order.getShippingCost())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .shippingAddress(OrderAddressMapper.toDTO(order.getShippingAddress()))
                .items(order.getItems().stream()
                        .map(i -> OrderItemDTO.builder()
                                .sku(i.getVariant().getSku())
                                .productName(i.getVariant().getName())
                                .quantity(i.getQuantity())
                                .unitLabel(i.getVariant().getUnitLabel())
                                .imageUrl(i.getVariant().getThumbImageUrl())
                                .price(i.getPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public static String generateOrderCode() {
        return "MIO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
