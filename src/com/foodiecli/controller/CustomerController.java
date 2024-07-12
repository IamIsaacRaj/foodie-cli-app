package com.foodiecli.controller;

import com.foodiecli.exceptions.CustomerExistsException;
import com.foodiecli.exceptions.CustomerNotFoundException;
import com.foodiecli.model.Customer;
import com.foodiecli.service.CustomerServiceImp;

import java.util.List;

public class CustomerController {
    private final CustomerServiceImp customerService;

    public CustomerController(CustomerServiceImp customerService) {
        this.customerService = customerService;
    }

    public Customer save(Customer customer) throws CustomerExistsException{
        return this.customerService.save(customer);
    }

    public  Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException{
        Customer customer = this.customerService.validateCustomerLogin(email, password);
        if (customer != null)
            this.customerService.setCurrentLoggedInCustomer(customer);
        return customer;
    }

    public  Customer cuurentLoggedInCustomer(){
        return this.customerService.getCurrentLoggedInCustomer();
    }

    public Customer getCustomerById(String id) throws CustomerNotFoundException{
        return this.customerService.getCustomerByID(id);
    }

    public List<Customer> getCustomerList(){
        return this.customerService.getAllCustomers();
    }

    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException{
        return this.customerService.updateCustomer(customer);
    }

    public void deleteCustomer(String id) throws CustomerNotFoundException{
        this.customerService.deleteCustomer(id);
    }
}
