package com.foodiecli.ui;

import com.foodiecli.controller.CustomerController;
import com.foodiecli.exceptions.CustomerExistsException;
import com.foodiecli.exceptions.CustomerNotFoundException;
import com.foodiecli.factory.Factory;
import com.foodiecli.model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu extends Menu{
    private final CustomerController customerController;

    public CustomerMenu() {
        this.customerController = Factory.getCustomerController();
    }

    @Override
    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO CUSTOMER SECTION");
                System.out.println();
                System.out.println("Please select the option..!");
                System.out.println("---------------------------");
                System.out.println("1. Register (New Customer)");
                System.out.println("2. Login (Existing Customer)");
                System.out.println("3. Search Customer");
                System.out.println("4. Display All Customers");
                System.out.println("5. Update Customer Details");
                System.out.println("7. Exit");
                System.out.println("Please Enter Your Choice 1 - 7");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> customerRegisterForm();
                    case 2 -> customerLoginForm();
                    case 3 -> customerSearchForm();
                    case 4 -> displayAllCustomer();
                    case 5 -> customerUpdateForm();
                    case 6 -> deleteCustomerForm();
                    case 7 -> {
                        System.out.println("Thanks You.See you again..!");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid Input.Please enter valid input from 1 - 7");
                }
            }
        }catch (Exception e){
            System.out.println("Some Internal Error Occurred. Please Enter the valid input from 1-7");
            displayMenu();
        }
    }

    public void displayCustomerDetails(Customer customer){
        displayMenuHeader("Customer Details");
        System.out.printf("%-10s %-30s %-80s %-30s \n", "ID", "NAME", "E-MAIL", "PASSWORD");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-30s \n", customer.getCustomerId(), customer.getCustomerName(),customer.getCustomerEmail(), "*".repeat(customer.getCustomerPassword().length()));
    }

    public void displayAllCustomer(){
        List<Customer> customerList = this.customerController.getCustomerList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Customer Details");
        System.out.printf("%-10s %-30s %-80s %-30s \n", "ID", "NAME", "E-MAIL", "PASSWORD");
        System.out.println(dashesLine);
        customerList.forEach(customer -> {
            System.out.printf("%-10s %-30s %-80s %-30s \n", customer.getCustomerId(), customer.getCustomerName(),customer.getCustomerEmail(), "*".repeat(customer.getCustomerPassword().length()));
        });

    }

    public void customerRegisterForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please register entering the details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter E-mail");
            String email = scanner.nextLine();
        /*  Console console = System.console();
            System.out.println("console : " + console);
            char[] passwordArray = console.readPassword("Enter Password (invisible)");
            String password = String.valueOf(passwordArray);    */
            System.out.println("Enter Password");
            String password = scanner.nextLine();
            Customer customer = new Customer();
            customer.setCustomerId(id)
                    .setCustomerName(name)
                    .setCustomerEmail(email)
                    .setCustomerPassword(password);
            Customer savedCustomer = customerController.save(customer);
            System.out.println("Customer Registration Successful");
            displayCustomerDetails(savedCustomer);
        }catch (CustomerExistsException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some Internal Error Occurred.Please try Again..!");
            customerRegisterForm();
        }
    }

    public void customerLoginForm(){
        try {
           Scanner scanner = new Scanner(System.in);
            System.out.println("Please Login Entering the details\n");
            System.out.println("Enter E-mail");
            String email = scanner.nextLine();
            System.out.println("Enter Password");
            String password = scanner.nextLine();
            Customer existingCustomer = customerController.validateCustomerLogin(email,password);
            System.out.println("Login Success");
            System.out.println("Welcome " + existingCustomer.getCustomerName());
        }catch (CustomerNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void customerSearchForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the details to search for Customer\n");
            System.out.println("Enter id");
            String id = scanner.nextLine();
            Customer customer = customerController.getCustomerById(id);
            displayCustomerDetails(customer);
        }catch (CustomerNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void customerUpdateForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the Details to Update the Customer details\n");
            System.out.println("Enter new Id");
            String id = scanner.nextLine();
            System.out.println("Enter new Name");
            String name = scanner.nextLine();
            System.out.println("Enter new E-mail");
            String email = scanner.nextLine();
            System.out.println("Enter new Password");
            String password = scanner.nextLine();
            Customer customer = new Customer();
            customer.setCustomerId(id)
                    .setCustomerName(name)
                    .setCustomerEmail(email)
                    .setCustomerPassword(password);
            Customer updatedCustomer = customerController.updateCustomer(customer);
            System.out.println("Customer details updated Successful");
            displayCustomerDetails(updatedCustomer);
        }catch (CustomerNotFoundException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some Internal Error Occurred.Please try Again..!");
            customerUpdateForm();
        }
    }

    public void deleteCustomerForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the details to details Customer\n");
            System.out.println("Enter id");
            String id = scanner.nextLine();
            customerController.deleteCustomer(id);
        }catch (CustomerNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }
}
