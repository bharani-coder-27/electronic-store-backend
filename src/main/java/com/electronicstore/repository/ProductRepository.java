package com.electronicstore.repository;

import com.electronicstore.model.Product;
import com.electronicstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products under a specific category
    List<Product> findByCategory(Category category);

    // Search by name (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
}
