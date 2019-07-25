package com.trilogyed.CustomerService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.CustomerService.dao.CustomerDao;
import com.trilogyed.CustomerService.dto.Customer;
import com.trilogyed.CustomerService.service.CustomerServiceLayer;
import com.trilogyed.CustomerService.viewModel.CustomerViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)

//@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerServiceLayer customerServiceLayer;


    @Test
    public void anyTest(){
        assertEquals(1, 1);
    }

    @Test
    public void getCustomerByIdReturnWithJSON() throws Exception {
        CustomerViewModel customer = new CustomerViewModel();
        customer.setFirst_name("Mock");
        customer.setLast_name("Mockovich");
        customer.setStreet("Mocking 234");
        customer.setCity("Mocka");
        customer.setZip("60611");
        customer.setEmail("mockingBird1@g.com");
        customer.setPhone("312445454");
        customer.setCustomer_id(5);


        when(customerServiceLayer.getCustomerById(5)).thenReturn(customer);
        ObjectMapper  objectMapper= new ObjectMapper();

        String outputJsone=objectMapper.writeValueAsString(customer);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/customers/{id}", 5)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(outputJsone));
    }
    @Test
    public  void createCustomerNeedsReturnCreateCustomer()throws Exception{
        CustomerViewModel customer= new CustomerViewModel();
        customer.setFirst_name("Mock");
        customer.setLast_name("Mockovich");
        customer.setStreet("Mocking 234");
        customer.setCity("Mocka");
        customer.setZip("60611");
        customer.setEmail("mockingBird1@g.com");
        customer.setPhone("312445454");
        ObjectMapper  objectMapper= new ObjectMapper();
        String inputJson= objectMapper.writeValueAsString(customer);
        CustomerViewModel customer1= new CustomerViewModel();
        customer1.setFirst_name("Mock");
        customer1.setLast_name("Mockovich");
        customer1.setStreet("Mocking 234");
        customer1.setCity("Mocka");
        customer1.setZip("60611");
        customer1.setEmail("mockingBird1@g.com");
        customer1.setPhone("312445454");
        customer1.setCustomer_id(5);
when(customerServiceLayer.createCustomer(customer)).thenReturn(customer1);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated());
    }

}
