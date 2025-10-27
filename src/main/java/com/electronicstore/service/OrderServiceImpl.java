package com.electronicstore.service;

import com.electronicstore.model.*;
import com.electronicstore.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;

    public OrderServiceImpl(OrderRepository orderRepo, OrderItemRepository orderItemRepo,
                            ProductRepository productRepo, CustomerRepository customerRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        // 1️⃣ Validate customer
        Customer customer = customerRepo.findById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // 2️⃣ Prepare order
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        double total = 0.0;

        // 3️⃣ Handle each item
        for (OrderItem item : order.getItems()) {
            Product product = productRepo.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // reduce stock
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepo.save(product);

            item.setOrder(order);
            item.setProduct(product);

            total += product.getPrice() * item.getQuantity();
        }

        order.setTotalAmount(total);
        return orderRepo.save(order);
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepo.findAll();
    }
}
