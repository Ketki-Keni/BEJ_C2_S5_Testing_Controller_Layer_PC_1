/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {
    @Id
    int customerId;
    String customerName;
    String customerPhoneNo;
    Product customerProduct;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerPhoneNo, Product customerProduct) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhoneNo = customerPhoneNo;
        this.customerProduct = customerProduct;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
    }

    public Product getCustomerProduct() {
        return customerProduct;
    }

    public void setCustomerProduct(Product customerProduct) {
        this.customerProduct = customerProduct;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNo='" + customerPhoneNo + '\'' +
                ", customerProduct=" + customerProduct +
                '}';
    }
}
