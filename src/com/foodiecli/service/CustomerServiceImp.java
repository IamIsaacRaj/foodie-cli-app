package com.foodiecli.service;

import com.foodiecli.exceptions.CustomerExistsException;
import com.foodiecli.exceptions.CustomerNotFoundException;
import com.foodiecli.model.Customer;
import com.foodiecli.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;
    private Customer currentLoggedInCustomer;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) throws CustomerExistsException {
        Optional<Customer> customerById = this.customerRepository.findCustomerById(customer.getCustomerId());
        if(customerById.isPresent())
            throw new CustomerExistsException("Customer Exists with this Id : " + customer.getCustomerId());
        return this.customerRepository.saveCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.getCustomerList();
    }

    @Override
    public Customer getCustomerByID(String id) throws CustomerNotFoundException {
        Optional<Customer> customerById = this.customerRepository.findCustomerById(id);
        if(customerById.isEmpty())
            throw new CustomerNotFoundException("Customer Exists with this Id : " + id);
        return customerById.get();
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
        Optional<Customer> customerById = this.customerRepository.findCustomerById(customer.getCustomerId());
        if(customerById.isEmpty())
            throw new CustomerNotFoundException("Customer Not Found with Id : " + customer.getCustomerId());
        return this.customerRepository.updateCustomer(customer);
    }
    @Override
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Optional<Customer> customerById = this.customerRepository.findCustomerById(id);
        if(customerById.isEmpty())
            throw new CustomerNotFoundException("Customer Not Found with Id : " + id);
        this.customerRepository.deleteCustomer(customerById.get());
    }

    @Override
    public Customer validateCustomerLogin(String email, String password) throws CustomerNotFoundException {
        Optional<Customer> customerByEmailAndPassword = this.customerRepository.findByEmailAndPassword(email,password);
        if(customerByEmailAndPassword.isEmpty())
            throw new CustomerNotFoundException("Invalid Email or Password");
        return customerByEmailAndPassword.get();
    }

    @Override
    public void setCurrentLoggedInCustomer(Customer customer) {
        this.currentLoggedInCustomer = customer;
    }

    @Override
    public Customer getCurrentLoggedInCustomer() {
        return this.currentLoggedInCustomer;
    }


}
