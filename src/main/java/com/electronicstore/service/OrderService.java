package com.electronicstore.service;

import com.electronicstore.model.OrderEntity;
import com.electronicstore.model.OrderItem;
import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderEntity order);
    OrderEntity getOrderById(Long id);
    List<OrderEntity> getAllOrders();
}
