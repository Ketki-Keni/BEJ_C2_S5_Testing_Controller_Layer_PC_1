package com.bej.product.service;

import com.bej.product.domain.Customer;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public List<Customer> getAllCustomerByProduct(String productName);
}
