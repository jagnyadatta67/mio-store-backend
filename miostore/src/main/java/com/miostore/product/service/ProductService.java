package com.miostore.product.service;


import com.miostore.product.entity.Product;
import com.miostore.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProduct(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = getProduct(id);

        return productRepo.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepo.findByCategoriesId(categoryId);
    }

}
