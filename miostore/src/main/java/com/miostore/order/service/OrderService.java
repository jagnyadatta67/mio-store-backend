package com.miostore.order.service;

import com.miostore.address.entity.Address;
import com.miostore.auth.SessionService;
import com.miostore.cart.repository.CartRepository;
import com.miostore.cart.service.CartService;
import com.miostore.order.dto.OrderDTO;
import com.miostore.order.entity.Cart;
import com.miostore.order.entity.Order;
import com.miostore.order.entity.OrderAddress;
import com.miostore.order.entity.OrderItem;
import com.miostore.order.mapper.OrderMapper;
import com.miostore.order.repository.OrderRepository;
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
    private final CartService cartService;





    @Transactional
    public OrderDTO createOrderFromCart(String paymentMethod) {
        Cart cart = cartService.getCart();
        if (cart == null) {
            throw new IllegalStateException("Cart not found for the current user");
        }

        if (cart.getShippingAddress() == null) {
            throw new IllegalStateException("No shipping address found");
        }

        // 🧠 Convert Cart → Order entity using Mapper
        Order order = OrderMapper.mapCartToOrder(cart, paymentMethod, DELIVERY_FEE);

        // 🧩 Persist Order
        Order savedOrder = orderRepository.save(order);

        // 🧹 Clear Cart after order placement
        cartRepository.delete(cart);

        // 🎯 Convert to DTO and return
        return OrderMapper.toOrderDTO(savedOrder);
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
