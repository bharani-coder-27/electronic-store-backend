package com.electronicstore.service;

import com.electronicstore.model.Customer;
import com.electronicstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return repo.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        return repo.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }
}
