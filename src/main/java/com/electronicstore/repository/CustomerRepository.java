package com.electronicstore.repository;

import com.electronicstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find by email (used during order placement)
    Customer findByEmail(String email);
}
