package com.miostore.cart.service;

import com.miostore.address.OrderAddressMapper;
import com.miostore.address.dto.AddressRequest;
import com.miostore.address.entity.Address;
import com.miostore.address.repository.AddressRepository;
import com.miostore.auth.SessionService;
import com.miostore.cart.repository.CartItemRepository;
import com.miostore.cart.repository.CartRepository;
import com.miostore.order.entity.Cart;
import com.miostore.order.entity.CartItem;
import com.miostore.product.entity.ProductVariant;
import com.miostore.product.repository.ProductVariantRepository;
import com.miostore.user.entity.User;
import com.miostore.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantRepository productVariantRepository;
    private final UserRepository userRepository;

    private final AddressRepository addressRepository;
    private final SessionService sessionService;

    /**
     * ✅ Add product to cart.
     * If the cart doesn't exist, create it first.
     */
    @Transactional
    public Cart addToCart( String variantProductId, Integer quantity) {
        // 1️⃣ Fetch user
        User user =  sessionService.getCurrentUser();

        // 2️⃣ Get or create cart
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> createNewCart(user));

        // 3️⃣ Fetch product
        ProductVariant product = productVariantRepository.findBySku(variantProductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 4️⃣ Check if product already exists in cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getSku().equals(variantProductId))
                .findFirst();

        if (existingItem.isPresent()) {
            // ✅ Update quantity
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // ✅ Add new item
            CartItem item = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .price(product.getSalePrice())
                    .build();
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateQuantity( String variantId, int quantity) {
        Cart cart = getCart();
        ProductVariant variant = productVariantRepository.findBySku(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        CartItem item = cartItemRepository.findByProductAndCartId(variant, cart.getId())
                .orElseThrow(() -> new RuntimeException("Item not found in cart"));

        item.setQuantity(quantity);
        cartItemRepository.save(item);
        return cartRepository.findById(cart.getId()).get();
    }

    @Transactional
    public void removeFromCart( String variantId) {
        Cart cart = getCart();
        ProductVariant variant = productVariantRepository.findBySku(variantId)
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        CartItem item = cartItemRepository.findByProductAndCartId(variant, cart.getId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cartItemRepository.delete(item);
    }

    @Transactional
    public Cart getCart() {
        User user =  sessionService.getCurrentUser();
        return cartRepository.findByUser(user)
                .orElseGet(() -> createNewCart(user));
    }

    /**
     * ✅ Helper: Create a new cart for a user.
     */
    private Cart createNewCart(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        return cartRepository.save(newCart);
    }

    public Cart attachAddressToCart( Long id) {
        Cart cart = getCart();
        Optional<Address> addressRequest=addressRepository.findById(id);
        cart.setShippingAddress(OrderAddressMapper.fromCustomerAddress(addressRequest.get()));
        return cartRepository.save(cart);
    }




}
