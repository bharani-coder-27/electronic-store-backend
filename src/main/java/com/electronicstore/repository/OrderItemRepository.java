package com.electronicstore.repository;

import com.electronicstore.model.OrderItem;
import com.electronicstore.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // Get all items in an order
    List<OrderItem> findByOrder(OrderEntity order);
}
