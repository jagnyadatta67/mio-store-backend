package com.miostore.cart.repository;

import com.miostore.order.entity.CartItem;
import com.miostore.order.entity.Cart;
import com.miostore.product.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // ✅ Find all items in a specific cart
    List<CartItem> findByCart(Cart cart);

    // ✅ Delete all items of a specific cart (optional for clearCart)
    void deleteByCart(Cart cart);
    Optional<CartItem> findByProductAndCartId(ProductVariant product, Long cartId);

}
