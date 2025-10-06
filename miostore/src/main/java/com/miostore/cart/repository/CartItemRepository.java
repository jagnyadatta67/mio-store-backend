package com.miostore.cart.repository;

import com.miostore.order.entity.CartItem;
import com.miostore.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // ✅ Find all items in a specific cart
    List<CartItem> findByCart(Cart cart);

    // ✅ Delete all items of a specific cart (optional for clearCart)
    void deleteByCart(Cart cart);
}
