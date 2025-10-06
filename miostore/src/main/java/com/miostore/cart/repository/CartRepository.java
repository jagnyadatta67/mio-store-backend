package com.miostore.cart.repository;



import com.miostore.order.entity.Cart;
import com.miostore.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // ✅ Find the cart for a specific user (One-to-One)
    Optional<Cart> findByUser(User user);
}
