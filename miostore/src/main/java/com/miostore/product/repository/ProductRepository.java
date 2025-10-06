package com.miostore.product.repository;



import com.miostore.product.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoriesId(Long categoryId);


    @EntityGraph(attributePaths = {"variants", "categories"})
    @Query("SELECT p FROM Product p")
    List<Product> findAllWithGraph();

}

