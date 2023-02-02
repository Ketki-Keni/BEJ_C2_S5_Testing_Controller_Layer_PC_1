/*
 * Author : Ketki Keni
 * Date : 01-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.service;

import com.bej.product.domain.Customer;
import com.bej.product.domain.Product;
import com.bej.product.exception.CustomerAlreadyExistsException;
import com.bej.product.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1,customer2;
    Product product;
    List<Customer> customerList;

    @BeforeEach
    void setUp() {
        product= new Product(110, "Samsung Phone", "Phone with charger and earphones");
        customer1 = new Customer(104, "Ketki", "8452037892", product);

        customer2 = new Customer(105, "Ben", "8452037892", product);
        customerList = Arrays.asList(customer1,customer2);
    }


    @AfterEach
    void tearDown() {
        product=null;
        customer1=null;
        customer2=null;
    }

    @Test
    public void givenCustomerToSaveReturnSavedCustomerSuccess() throws CustomerAlreadyExistsException {
        when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.ofNullable(null));
        when(customerRepository.save(any())).thenReturn(customer1);
        assertEquals(customer1,customerService.saveCustomer(customer1));
        verify(customerRepository,times(1)).save(any());
        verify(customerRepository,times(1)).findById(any());
    }



    @Test
    public void givenCustomerToSaveReturnCustomerFailure(){
        when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.ofNullable(customer1));
        assertThrows(CustomerAlreadyExistsException.class,()->customerService.saveCustomer(customer1));
        verify(customerRepository,times(0)).save(any());
        verify(customerRepository,times(1)).findById(any());
    }

    @Test
    public void givenProductNameReturnAllCustomersByProductSuccess() {
        when(customerRepository.findAllCustomerByCustomerProduct(customer1.getCustomerProduct().getProductName())).thenReturn(customerList);
        List<Customer> list = customerRepository.findAllCustomerByCustomerProduct(customer1.getCustomerProduct().getProductName());
        assertEquals("Samsung Phone",list.get(1).getCustomerProduct().getProductName());

        verify(customerRepository,times(1)).findAllCustomerByCustomerProduct(any());
    }
}
