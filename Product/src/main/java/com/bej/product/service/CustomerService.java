package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.exception.ProductNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException;
    public List<Customer> getAllCustomers();
    public List<Customer> getAllCustomerByProduct(String productName) throws ProductNotFoundException;
}
