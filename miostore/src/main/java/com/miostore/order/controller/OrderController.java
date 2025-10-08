package com.miostore.order.controller;

import com.miostore.auth.SessionService;
import com.miostore.order.dto.OrderDTO;
import com.miostore.order.service.OrderService;
import com.miostore.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SessionService sessionService;
    /**
     * ✅ Fetch user's order history
     */
    @GetMapping("/history")
    public ResponseEntity<List<OrderDTO>> getUserOrderHistory() {
        User user = sessionService.getCurrentUser();
        List<OrderDTO> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/confirm")
    public ResponseEntity<OrderDTO> confirmOrder(
            @RequestParam String paymentMethod
    ) {
        OrderDTO order = orderService.createOrderFromCart( paymentMethod);
        return ResponseEntity.ok(order);
    }
}
