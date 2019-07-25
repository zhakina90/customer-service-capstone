package com.trilogyed.CustomerService.service;

import com.trilogyed.CustomerService.dao.CustomerDao;
import com.trilogyed.CustomerService.dao.CustomerJdbcTemplateImpl;
import com.trilogyed.CustomerService.dto.Customer;
import com.trilogyed.CustomerService.viewModel.CustomerViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceLayerTest {
    @Mock
    CustomerDao customerDao;

    CustomerServiceLayer customerServiceLayer;


    @Before
    public void setUp() throws Exception {
        setUpCustomerServiceMock();
customerServiceLayer= new CustomerServiceLayer (customerDao);
    }

    public void setUpCustomerServiceMock() {
//        customerDao = mock(CustomerJdbcTemplateImpl.class);
        Customer customer = new Customer();
        customer.setCustomer_id(1);
        customer.setFirst_name("Zhamal");
        customer.setLast_name("Nurdin");
        customer.setStreet("234 Main Street");
        customer.setCity("Chicago");
        customer.setZip("60645");
        customer.setEmail("northwester123@g.com");
        customer.setPhone("3159875678");

        Customer customer1 = new Customer();
        customer1.setFirst_name("Zhamal");
        customer1.setLast_name("Nurdin");
        customer1.setStreet("234 Main Street");
        customer1.setCity("Chicago");
        customer1.setZip("60645");
        customer1.setEmail("northwester123@g.com");
        customer1.setPhone("3159875678");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
//        doReturn(customer).when(customerServiceLayer).createCustomer(customer1);
        doReturn(customer).when(customerServiceLayer).getCustomerById(customer.getCustomer_id());
        doReturn(customerList).when(customerServiceLayer).getAllCustomers();

    }

    @Test
    public void createGetCustomer() {
        //assemble
        CustomerViewModel customerViewModel = new CustomerViewModel();
//        customerViewModel.setCustomer_id(1);
        customerViewModel.setFirst_name("Zhamal");
        customerViewModel.setLast_name("Nurdin");
        customerViewModel.setStreet("234 Main Street");
        customerViewModel.setCity("Chicago");
        customerViewModel.setZip("60645");
        customerViewModel.setEmail("northwester123@g.com");
        customerViewModel.setPhone("3159875678");
        //act
        customerViewModel = customerServiceLayer.createCustomer(customerViewModel);
        CustomerViewModel customerViewModel1 = customerServiceLayer.getCustomerById(customerViewModel.getCustomer_id());

        //assert
        assertEquals(customerViewModel1, customerViewModel);
    }


    @Test
    public void getAllCustomers() {
        //assemble
        CustomerViewModel customerViewModel = new CustomerViewModel();
//        customerViewModel.setCustomer_id(1);
        customerViewModel.setFirst_name("Zhamal");
        customerViewModel.setLast_name("Nurdin");
        customerViewModel.setStreet("234 Main Street");
        customerViewModel.setCity("Chicago");
        customerViewModel.setZip("60645");
        customerViewModel.setEmail("northwester123@g.com");
        customerViewModel.setPhone("3159875678");
        //act
        customerViewModel = customerServiceLayer.createCustomer(customerViewModel);
//        List<CustomerViewModel> customerViewModelList = customerServiceLayer.getCustomerById(customerViewModel.getCustomer_id());
        //assert
//        assertEquals(customerViewModelList.size(), 2);




    }

}