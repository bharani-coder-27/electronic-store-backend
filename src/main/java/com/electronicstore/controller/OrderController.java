package com.electronicstore.controller;

import com.electronicstore.dto.OrderDTO;
import com.electronicstore.model.Customer;
import com.electronicstore.model.OrderEntity;
import com.electronicstore.model.OrderItem;
import com.electronicstore.model.Product;
import com.electronicstore.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO dto) {
        OrderEntity order = new OrderEntity();
        Customer customer = new Customer();
        customer.setId(dto.getCustomerId());
        order.setCustomer(customer);

        List<OrderItem> items = dto.getItems().stream().map(itemDto -> {
            OrderItem item = new OrderItem();
            Product product = new Product();
            product.setId(itemDto.getProductId());
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setOrder(order);
            return item;
        }).toList();

        order.setItems(items);

        OrderEntity saved = service.createOrder(order);

        // Build response DTO
        OrderDTO response = new OrderDTO();
        response.setId(saved.getId());
        response.setCustomerId(saved.getCustomer().getId());
        response.setTotalAmount(saved.getTotalAmount());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }
}
