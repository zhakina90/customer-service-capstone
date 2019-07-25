package com.trilogyed.CustomerService.service;

import com.trilogyed.CustomerService.dao.CustomerDao;
import com.trilogyed.CustomerService.dto.Customer;
import com.trilogyed.CustomerService.viewModel.CustomerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerServiceLayer {
     @Autowired
   private CustomerDao customerDao;

    @Autowired
    public CustomerServiceLayer(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    private CustomerViewModel buldCustomerViewModel(Customer customer) {
        CustomerViewModel customerViewModel = new CustomerViewModel();
        customerViewModel.setCustomer_id(customer.getCustomer_id());
        customerViewModel.setFirst_name(customer.getFirst_name());
        customerViewModel.setLast_name(customer.getLast_name());
        customerViewModel.setStreet(customer.getStreet());
        customerViewModel.setCity(customer.getCity());
        customerViewModel.setZip(customer.getZip());
        customerViewModel.setEmail(customer.getEmail());
        customerViewModel.setPhone(customer.getPhone());
        return customerViewModel;
    }

    @Transactional
    public CustomerViewModel createCustomer(CustomerViewModel customerViewModel) {
        Customer customer = new Customer();
        customer.setFirst_name(customerViewModel.getFirst_name());
        customer.setLast_name(customerViewModel.getLast_name());
        customer.setStreet(customerViewModel.getStreet());
        customer.setCity(customerViewModel.getCity());
        customer.setZip(customerViewModel.getZip());
        customer.setEmail(customerViewModel.getEmail());
        customer.setPhone(customerViewModel.getPhone());
        customer = customerDao.createCustomer(customer);
        customerViewModel.setCustomer_id(customer.getCustomer_id());
        return customerViewModel;
    }

    public void updateCustomer(CustomerViewModel customerViewModel) {
        Customer customer = new Customer();
        customer.setCustomer_id(customerViewModel.getCustomer_id());
        customer.setFirst_name(customerViewModel.getFirst_name());
        customer.setLast_name(customerViewModel.getLast_name());
        customer.setStreet(customerViewModel.getStreet());
        customer.setCity(customerViewModel.getCity());
        customer.setZip(customerViewModel.getZip());
        customer.setEmail(customerViewModel.getEmail());
        customer.setPhone(customerViewModel.getPhone());
        customerDao.updateCutomer(customer);

    }

    public CustomerViewModel getCustomerById(int id) {
        Customer customer = customerDao.getCustomerById(id);
        if (customer == null) {
            throw new IllegalArgumentException("Entered customer is not exist " + id);
        } else {
            return buldCustomerViewModel(customer);
        }

    }

    public List<CustomerViewModel> getAllCustomers() {
        List<Customer> customerList = customerDao.getAllCustomers();
        List<CustomerViewModel> customerViewModelList = new ArrayList<>();
        for (Customer c : customerList) {
            customerViewModelList.add(buldCustomerViewModel(c));
        }
        return customerViewModelList;
    }

    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

}
