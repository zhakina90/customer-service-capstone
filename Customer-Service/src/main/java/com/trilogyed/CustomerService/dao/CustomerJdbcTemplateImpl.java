package com.trilogyed.CustomerService.dao;

import com.trilogyed.CustomerService.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerJdbcTemplateImpl implements CustomerDao {
    public static final String INSERT_CUSTOMER =
            "insert into customer (first_name, last_name, street, city, zip, email, phone) values(?, ?, ?, ?, ?, ?,?)";
    public static final String GET_CUSTOMER_BY_ID =
            "select * from customer where customer_id =?";
    public static final String GET_ALL_CUSTOMERS =
            "select * from customer;";
    public static final String UPDATE_CUSTOMER =
            "update customer set first_name=?, last_name=?, street=?, city=?, zip=?, email=?, phone=? where customer_id=?";
    public static final String DELETE_CUSTOMER =
            "delete from customer where customer_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CustomerJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Customer mapRowToCustomer(ResultSet resultSet, int numRow) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomer_id(resultSet.getInt("customer_id"));
        customer.setFirst_name(resultSet.getString("first_name"));
        customer.setLast_name(resultSet.getString("last_name"));
        customer.setStreet(resultSet.getString("street"));
        customer.setCity(resultSet.getString("city"));
        customer.setZip(resultSet.getString("zip"));
        customer.setEmail(resultSet.getString("email"));
        customer.setPhone(resultSet.getString("phone"));
        return customer;
    }


    //    customer_id int(11) not null auto_increment primary key,
//    first_name varchar(50) not null,
//    last_name varchar(50) not null,
//    street varchar(50) not null,
//    city varchar(50) not null,
//    zip varchar(10) not null,
//    email varchar(75) not null,
//    phone varchar(20) not null

    @Override
    public Customer createCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER,
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        customer.setCustomer_id(id);
        return customer;
    }

    @Override
    public Customer getCustomerById(int id) {
        try{
            return jdbcTemplate.queryForObject(GET_CUSTOMER_BY_ID, this::mapRowToCustomer, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(GET_ALL_CUSTOMERS, this::mapRowToCustomer);
    }

    @Override
    public void updateCutomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER,
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getCustomer_id());

    }

    @Override
    public void deleteCustomer(int id) {
        jdbcTemplate.update(DELETE_CUSTOMER, id);
    }
}
