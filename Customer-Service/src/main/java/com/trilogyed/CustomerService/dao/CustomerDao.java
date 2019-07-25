package com.trilogyed.CustomerService.dao;

import com.trilogyed.CustomerService.dto.Customer;

import java.util.List;


public interface CustomerDao {
    Customer createCustomer(Customer customer);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    void updateCutomer(Customer customer);
    void deleteCustomer(int id);
}
