/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.controller;

import com.bej.product.domain.Customer;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.exception.ProductNotFoundException;
import com.bej.product.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/demo")
    public ResponseEntity<String> get(){
        return new ResponseEntity<String>("Sample Demo",HttpStatus.OK);
    }

    //Uri : http://localhost:8083/api/v1/customer : Method : Post
    @PostMapping("/customer")
    public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
        Customer customer1 = customerService.saveCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    //Uri : http://localhost:8083/api/v1/customers : Method : Get
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    ////Uri : http://localhost:8083/api/v1/CustomerByProduct/Samsung Phone : Method : Get
    @GetMapping("/CustomerByProduct/{customerProduct}")
    public ResponseEntity<?> getAllCustomerByProduct(@PathVariable String customerProduct) throws ProductNotFoundException {
        List<Customer> customers=customerService.getAllCustomerByProduct(customerProduct);
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
}
