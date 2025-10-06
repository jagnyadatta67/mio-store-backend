package com.miostore.product.controller;




import com.miostore.product.dto.ProductDTO;
import com.miostore.product.entity.Product;
import com.miostore.product.mapper.ProductMapper;
import com.miostore.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")

@Tag(name = "Products", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "List all products")
    public List<ProductDTO> getAll() {
        return ProductMapper.toDTOList(productService.getAllProducts());


    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    public Product getById(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get products by category")
    public List<Product> getByCategory(@PathVariable Long categoryId) {
        return productService.getByCategory(categoryId);
    }
}
