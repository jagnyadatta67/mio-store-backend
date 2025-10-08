package com.miostore.order.repository;

import com.miostore.order.entity.Order;
import com.miostore.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // 🧾 Fetch all orders for a given user
    List<Order> findByUser_Email(String email);

    // 🔍 Find an order by its unique order number
    Optional<Order> findByCode(String code);
    List<Order> findByUserOrderByCreatedAtDesc(User user);
}
