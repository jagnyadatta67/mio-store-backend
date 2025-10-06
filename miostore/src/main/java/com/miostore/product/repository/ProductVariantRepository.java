package com.miostore.product.repository;
import com.miostore.product.entity.Product;
import com.miostore.product.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    // ✅ Get all variants of a specific product
    List<ProductVariant> findByProduct(Product product);

    // ✅ Example: Get variants by SKU if your variants have unique SKUs
    Optional<ProductVariant> findBySku(String sku);

    // ✅ Optional custom query example (if you use native SQL)
    @Query("SELECT v FROM ProductVariant v WHERE v.product.id = :productId")
    List<ProductVariant> findAllByProductId(Long productId);

}

