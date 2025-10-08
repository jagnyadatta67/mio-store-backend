package com.miostore.order.dto;


import com.miostore.address.dto.AddressRequest;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private String orderNumber;
    private double subtotal;
    private double totalAmount;
    private double deliveryFee;
    private String paymentMethod;
    private String paymentStatus;
    private AddressRequest shippingAddress;
    private List<OrderItemDTO> items;
    private String orderStatus;
}
