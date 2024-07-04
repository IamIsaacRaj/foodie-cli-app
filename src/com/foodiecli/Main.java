package com.foodiecli;

import com.foodiecli.factory.Factory;
import com.foodiecli.model.Customer;
import com.foodiecli.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        /*CsvReader reader = new CsvReader();

        List<Customer> customers = reader.readCustomerFromCSV();
        customers.forEach(System.out::println);

        List<Restaurant> restaurants= reader.readRestaurantsFromCSV();
        restaurants.forEach(System.out::println);

        List<Dish> dishes = reader.readDishesFromCsv();
        dishes.forEach(System.out::println);*/


        CustomerRepository customerRepository = Factory.getCustomerRepository();

        List<Customer> customerList = customerRepository.getCustomerList();
        System.out.println("Customer List");
        customerList.forEach(System.out::println);

        Customer newCustomer = new Customer();
        newCustomer.setCustomerId("C011")
                .setCustomerName("Isaac Raj")
                .setCustomerEmail("Isaac.Raj@gmail.com")
                .setCustomerPassword("Raju@1234");
        Customer saveCustomer = customerRepository.saveCustomer(newCustomer);
        System.out.println("\nCustomer List after adding a new customer:");
        customerList.forEach(System.out::println);

        String id = "C012";
        Optional<Customer> customerById = customerRepository.getCustomerById(id);
        System.out.println("\nGetting Customer by Id :" + id);
        customerById.ifPresentOrElse(System.out::println,
                                     ()-> System.out.println("Customer not found with Id : " + id));

        String email = "john.doe@example.com";
        Optional<Customer> customerByEmail = customerRepository.getCustomerByEmail(email);
        System.out.println("\nGetting Customer by Email :" + email);
        customerByEmail.ifPresentOrElse(System.out::println,
                                     ()-> System.out.println("Customer not found with Email : " + email));

        String email1 = "Isaac.Raj@gmail.com";
        String passwd = "Raju@1234";
        Optional<Customer> findCustomer = customerRepository.findByEmailAndPassword(email1,passwd);
        System.out.println("\nGetting Customer by Email :" + email);
        findCustomer.ifPresentOrElse(System.out::println,
                            ()-> System.out.println("Customer not found with Email : " + email));

    }
}
