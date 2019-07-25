package com.trilogyed.CustomerService.dao;

import com.trilogyed.CustomerService.dto.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {
@Autowired
CustomerDao customerDao;
    @Before
    public void setUp() throws Exception {
        List<Customer> customerList = customerDao.getAllCustomers();
        customerList.stream()
                .forEach(customer -> customerDao.deleteCustomer(customer.getCustomer_id()));
     }

    @Test
    public void createGetCustomer() {
        //assemble
        Customer customer= new Customer();
        customer.setFirst_name("Zhamal");
        customer.setLast_name("Nurdin");
        customer.setStreet("Main Street");
        customer.setCity("Chicago");
        customer.setZip("60611");
        customer.setPhone("3128907651");
        customer.setEmail("northwerstern123@g.com");

        //act
        customer= customerDao.createCustomer(customer);
        Customer customer1= customerDao.getCustomerById(customer.getCustomer_id());

        assertEquals(customer1, customer);
    }

    @Test
    public void getAllCustomers() {
        //assemble
        Customer customer= new Customer();
        customer.setFirst_name("Zhamal");
        customer.setLast_name("Nurdin");
        customer.setStreet("Main Street");
        customer.setCity("Chicago");
        customer.setZip("60611");
        customer.setPhone("3128907651");
        customer.setEmail("northwerstern123@g.com");

        //act
        customer= customerDao.createCustomer(customer);
        List<Customer> customerList= customerDao.getAllCustomers();
        //assert
        assertEquals(customerList.size(), 1);
    }

    @Test
    public void updateCutomer() {
        //assemble
        Customer customer= new Customer();
        customer.setFirst_name("Zhamal");
        customer.setLast_name("Nurdin");
        customer.setStreet("Main Street");
        customer.setCity("Chicago");
        customer.setZip("60611");
        customer.setPhone("3128907651");
        customer.setEmail("northwerstern123@g.com");
        //act
        customer= customerDao.createCustomer(customer);
        //assemble
        customer.setFirst_name("Zhamal");
        customer.setLast_name("Nurdin");
        customer.setStreet("Main Street");
        customer.setCity("Chicago");
        customer.setZip("60611");
        customer.setPhone("3128907651");
        customer.setEmail("northwerstern123@g.com");

        //act
        customerDao.updateCutomer(customer);
        Customer customer1 = customerDao.getCustomerById(customer.getCustomer_id());

        //assert
        assertEquals(customer1, customer);

    }

    @Test
    public void deleteCustomer() {
        //assemble
        Customer customer= new Customer();
        customer.setFirst_name("Zhamal");
        customer.setLast_name("Nurdin");
        customer.setStreet("Main Street");
        customer.setCity("Chicago");
        customer.setZip("60611");
        customer.setPhone("3128907651");
        customer.setEmail("northwerstern123@g.com");

        //act
        customer= customerDao.createCustomer(customer);
        customerDao.deleteCustomer(customer.getCustomer_id());
        Customer customer1 = customerDao.getCustomerById(customer.getCustomer_id());

        //assert
        assertNull(customer1);
    }
}