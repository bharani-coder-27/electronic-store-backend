package com.electronicstore.repository;

import com.electronicstore.model.OrderEntity;
import com.electronicstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // Get all orders of a particular customer
    List<OrderEntity> findByCustomer(Customer customer);
}
