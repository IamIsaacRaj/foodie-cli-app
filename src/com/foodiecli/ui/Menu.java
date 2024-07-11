package com.foodiecli.ui;

import com.foodiecli.model.Customer;

import java.util.Scanner;

public class Menu {
    public void displayMainMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("------------------------------------------------------------------");
            System.out.println("                WELCOME TO FOODIE APP                             ");
            System.out.println("------------------------------------------------------------------");

            System.out.println();
            System.out.println("Please Select the Option..!");
            System.out.println("---------------------------");
            System.out.println("1.Register (New Customer)");
            System.out.println("2.Login (Existing Customer)");
            System.out.println("3.View Restaurants");
            System.out.println("4.View Menu");
            System.out.println("5.Place Order");
            System.out.println("6.View Orders");
            System.out.println("7.Exit");

            System.out.println("Please Enter Your Choice (1-7)");

            int input = scanner.nextInt();
            switch (input){
                case 1: displayRegisterMenu();
                        break;
                case 2: displayLoginMenu();
                        break;
                case 3: displayRestaurants();
                        break;
                case 4: displayMenuDetails();
                        break;
                case 5: placeOrderMenu();
                        break;
                case 6: viewOrdersMenu();
                        break;
                case 7: System.out.println("Thank you for choosing Foodie App ! See you Again !");
                        System.exit(0);
                        break;
                case 8: System.out.println("Invalid Input. Please enter the valid input from(1-7)");

            }
        }
    }

    private void placeOrderMenu(){
    }

    private void viewOrdersMenu(){
    }

    private void displayMenuDetails() {
    }

    private void displayRestaurants() {
    }

    private void displayLoginMenu(){
    }

    public Menu(){
    }

    private void displayRegisterMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Thanks for Trusting Foodie App");
        System.out.println("Enter Id :");
        String id = scanner.nextLine();
        System.out.println("Enter Name :");
        String name = scanner.nextLine();
        System.out.println("Enter Email :");
        String Email = scanner.nextLine();
        /*Console console = System.console();
        System.out.println("console : " + console);
        char[] passwordArray = console.readPassword("Enter Password (invisible)");
        String password = String.valueOf(passwordArray);*/
        System.out.println("Enter Password :");
        String Password = scanner.nextLine();

        Customer customer = new Customer();
        customer.setCustomerId(id)
                .setCustomerName(name)
                .setCustomerEmail(Email)
                .setCustomerPassword(Password);

    }
}
