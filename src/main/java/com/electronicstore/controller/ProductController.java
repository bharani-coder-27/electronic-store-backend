package com.electronicstore.controller;

import com.electronicstore.dto.ProductDTO;
import com.electronicstore.model.Product;
import com.electronicstore.model.Category;
import com.electronicstore.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        Category category = new Category();
        category.setId(dto.getCategoryId());
        product.setCategory(category);

        Product saved = service.addProduct(product);

        // Convert back to DTO
        ProductDTO response = new ProductDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());
        response.setPrice(saved.getPrice());
        response.setQuantity(saved.getQuantity());
        response.setCategoryId(saved.getCategory().getId());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(service.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.getProductsByCategory(categoryId));
    }

    @PutMapping("/{id}/stock/increase")
    public ResponseEntity<Product> increaseStock(@PathVariable Long id, @RequestParam int amount) {
        Product updated = service.adjustStock(id, amount);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/stock/decrease")
    public ResponseEntity<Product> decreaseStock(@PathVariable Long id, @RequestParam int amount) {
        Product updated = service.adjustStock(id, -amount);
        return ResponseEntity.ok(updated);
    }

}
