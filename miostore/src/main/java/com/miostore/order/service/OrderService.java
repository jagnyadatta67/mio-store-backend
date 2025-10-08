package com.miostore.order.service;

import com.miostore.address.entity.Address;
import com.miostore.address.repository.AddressRepository;
import com.miostore.address.util.AddressCloneUtil;
import com.miostore.auth.SessionService;
import com.miostore.cart.repository.CartRepository;
import com.miostore.cart.service.CartService;
import com.miostore.order.dto.OrderDTO;
import com.miostore.order.entity.*;
import com.miostore.order.mapper.OrderMapper;
import com.miostore.order.repository.OrderRepository;
import com.miostore.order.util.OfferCloneUtil;
import com.miostore.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final double DELIVERY_FEE = 40.0;

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final SessionService sessionService;
    private final AddressRepository  addressRepository;
    private final CartService cartService;





    public OrderDTO createOrderFromCart(String paymentMethod) {
        Cart cart = cartService.getCart();
        if (cart == null) throw new IllegalStateException("Cart not found");

        Address original = cart.getShippingAddress();
        if (original == null) throw new IllegalStateException("No shipping address found");

        // ✅ Clone new address for order
        Address clonedAddress = addressRepository.save(
                AddressCloneUtil.cloneForOrder(original, cart.getUser())
        );

        double subtotal = cart.getItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        double total = subtotal + DELIVERY_FEE;

        List<OrderItem> orderItems = OrderMapper.mapToOrderItems(cart.getItems());

        Order order = Order.builder()
                .code(OrderMapper.generateOrderCode())
                .user(cart.getUser())
                .shippingAddress(clonedAddress)
                .items(orderItems)
                .subTotal(subtotal)
                .shippingCost(DELIVERY_FEE)
                .totalAmount(total)
                .paymentMethod(paymentMethod)
                .paymentStatus(paymentMethod.equalsIgnoreCase("COD") ? "Pending" : "Paid")
                .orderStatus("Confirmed")
                .createdAt(LocalDateTime.now())
                .build();

        orderItems.forEach(i -> i.setOrder(order));

        if (cart.getAppliedOffer() != null) {
            OfferSnapshot snapshot = OfferCloneUtil.cloneFromOffer(cart.getAppliedOffer());
            order.setAppliedOffer(snapshot);
        }
        Order saved = orderRepository.save(order);

        cartRepository.delete(cart);

        return OrderMapper.toOrderDTO(saved);
    }
    /**
     * ✅ Get all orders for the logged-in user
     */
    public List<OrderDTO> getOrdersByUser(User user) {

        List<Order> orders = orderRepository.findByUserOrderByCreatedAtDesc(user);

        return orders.stream()
                .map(OrderMapper::toOrderDTO
                )
                .toList();
    }
}
