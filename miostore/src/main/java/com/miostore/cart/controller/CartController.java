package com.miostore.cart.controller;

import com.miostore.auth.JwtService;
import com.miostore.cart.dto.CartDTO;
import com.miostore.cart.dto.CartRequest;
import com.miostore.cart.mapper.CartMapper;
import com.miostore.cart.service.CartService;
import com.miostore.order.entity.Cart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Cart management APIs")
public class CartController {

    private final CartService cartService;
    private final JwtService jwtService;

    @PostMapping("/add")
    @Operation(summary = "Add product to cart")
    public ResponseEntity<CartDTO> addToCart(
            @RequestBody CartRequest request) {

         Cart cart = cartService.addToCart(
                request.getVariant(), request.getQuantity());
        return ResponseEntity.ok(CartMapper.toDTO(cart));
    }

    @GetMapping
    @Operation(summary = "Get current user's cart")
    public ResponseEntity<CartDTO> getCart() {
        return ResponseEntity.ok(CartMapper.toDTO(cartService.getCart()));
    }
}
