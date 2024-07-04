package com.foodiecli.repository;

import com.foodiecli.factory.Factory;
import com.foodiecli.model.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerRepository {
    List<Customer> customerList;

    public CustomerRepository() {
        this.customerList = Factory.getCsvReader().readCustomerFromCSV();
    }
    public List<Customer> getCustomerList(){
       return this.customerList;
    }

    public Customer saveCustomer(Customer customer){
        this.customerList.add(customer);
        return customer;
    }

    public Optional<Customer> getCustomerById(String id){
        return this.customerList.stream()
                    .filter(customer -> customer.getCustomerId().equals(id))
                    .findFirst();
    }

    public Optional<Customer> getCustomerByEmail(String email){
        return this.customerList.stream()
                    .filter(customer -> customer.getCustomerEmail().equalsIgnoreCase(email))
                    .findFirst();
    }

    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        return this.customerList.stream()
                .filter(customer -> customer.getCustomerEmail().equalsIgnoreCase(email) &&
                                             customer.getCustomerPassword().equals(password))
                .findFirst();
    }

}
