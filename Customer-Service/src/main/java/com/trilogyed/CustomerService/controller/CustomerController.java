package com.trilogyed.CustomerService.controller;

import com.trilogyed.CustomerService.service.CustomerServiceLayer;
import com.trilogyed.CustomerService.viewModel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RefreshScope
public class CustomerController {
    @Autowired
    CustomerServiceLayer customerServiceLayer;


    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public CustomerViewModel createCustomer(@RequestBody CustomerViewModel customerViewModel) {
        return customerServiceLayer.createCustomer(customerViewModel);
    }
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CustomerViewModel> getAllCustomers() {
        List<CustomerViewModel> customerViewModelList = customerServiceLayer.getAllCustomers();
        if (customerViewModelList.size() == 0 || customerViewModelList == null) {
            throw new IllegalArgumentException("Customer is not exist!");
        }
        return customerViewModelList;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerViewModel customerViewModel) {
        if (customerViewModel.getCustomer_id() != id) {
            throw new IllegalArgumentException("Provided customer " + id + " is not exist");
        } else {
            customerServiceLayer.updateCustomer(customerViewModel);
        }
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerViewModel getCustomerById(@PathVariable int id) {
        CustomerViewModel customerViewModel = customerServiceLayer.getCustomerById(id);
        if (customerViewModel == null) {
            throw new IllegalArgumentException("Provided customer " + id + " is not exist");
        } else {
            customerServiceLayer.getCustomerById(id);
        }
        return customerViewModel;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id) {
       customerServiceLayer.deleteCustomer(id);
    }
}
