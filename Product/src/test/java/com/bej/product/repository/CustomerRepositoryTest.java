/*
 * Author : Ketki Keni
 * Date : 01-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.repository;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private Product product;

    @BeforeEach
    void setInitialization(){
        product= new Product(110, "Samsung Phone", "Phone with charger and earphones");
        customer= new Customer(104, "Ketki", "8452037892", product);
    }

    @AfterEach
    void tearDown(){
        System.out.println("================Set TearDown Called==================");
        product=null;
        customer=null;
    }

    @Test
    @DisplayName("Test Case for Saving Customer Object")
    public void givenCustomerToSaveReturnSavedCustomerSuccess(){
        customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        assertNotNull(customer1);
        assertEquals(customer.getCustomerId(), customer1.getCustomerId());
    }

    @Test
    @DisplayName("Test case for retrieving all the customer object")
    public void givenCustomerReturnAllCustomerDetailsSuccess(){
        customerRepository.insert(customer);
        Product product1 = new Product(110, "Samsung Phone", "Phone with charger and earphones");
        Customer customer1 = new Customer(105, "Ben", "8452037892", product1);
        customerRepository.insert(customer1);

        List<Customer> list = customerRepository.findAll();
        assertEquals(2, list.size());
        assertEquals("Ben", list.get(1).getCustomerName());
    }

    @Test
    @DisplayName("Test case for retrieving all the customer object")
    public void givenCustomerReturnAllCustomerDetailsFailure(){
        List<Customer> list = customerRepository.findAll();
        assertNotEquals(2, list.size());
        assertNotEquals("Tom", list.get(1).getCustomerName());
    }
    @Test
    @DisplayName("Test case for retrieving all the customers by product name")
    public void givenProductNameReturnAllCustomerDetailsSuccess(){
        List<Customer> list = customerRepository.findAllCustomerByCustomerProduct("Samsung Phone");
        assertEquals("Samsung Phone",list.get(1).getCustomerProduct().getProductName());
    }

    @Test
    @DisplayName("Test case for retrieving all the customers by product name")
    public void givenProductNameReturnAllCustomerDetailsFailure(){
        List<Customer> list = customerRepository.findAllCustomerByCustomerProduct("Samsung Phone");
        assertNotEquals("Airpods",list.get(1).getCustomerProduct().getProductName());
    }


}
