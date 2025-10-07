package com.miostore.suggestion;



import com.miostore.order.entity.Cart;
import com.miostore.cart.repository.CartRepository;
import com.miostore.product.entity.Product;
import com.miostore.product.entity.ProductVariant;
import com.miostore.product.repository.ProductRepository;
import com.miostore.product.repository.ProductVariantRepository;
import com.miostore.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartSuggestionService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;

    public List<ProductVariant> getSuggestions() {


       /* // If no cart → return top-rated products
        if (cart == null || cart.getItems().isEmpty()) {
            return productRepository.findTop3ByOrderByRatingDesc();
        }

        // Extract category IDs from cart items
        Set<Long> categoryIds = cart.getItems().stream()
                .flatMap(item -> item.getVariant().getProduct().getCategories().stream())
                .map(c -> c.getId())
                .collect(Collectors.toSet());

        // Fetch products from those categories (exclude existing cart items)
        Set<Long> cartProductIds = cart.getItems().stream()
                .map(item -> item.getVariant().getProduct().getId())
                .collect(Collectors.toSet());

        List<Product> relatedProducts = productRepository
                .findTop3ByCategories_IdInAndIdNotInOrderByRatingDesc(categoryIds, cartProductIds);

        // fallback if no related found
        if (relatedProducts.isEmpty()) {
            relatedProducts = productRepository.findTop3ByOrderByRatingDesc();
        }*/

        ProductVariant productVariant=  productVariantRepository.findBySku("SOAP-JASMINE-001-75G").get();

        return List.of(productVariant);
    }
}
