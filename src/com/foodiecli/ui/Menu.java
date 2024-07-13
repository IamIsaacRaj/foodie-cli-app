package com.foodiecli.ui;

import com.foodiecli.model.Customer;
import com.foodiecli.model.Restaurant;

import java.util.Scanner;

public class Menu {
    public Menu() {

    }

    public void displayMenuHeader(String header){
        printDashLine();
        String spaces = new String(new char[70]).replace('\0',' ');
        System.out.printf("%-40s %-10s %-40s \n",spaces,header,spaces);
        printDashLine();
    }

    public  void printDashLine(){
        String dashLines = new String(new char[150]).replace('\0','-');
        System.out.println(dashLines);
    }

    public void displayMenu(){
        try {
            Scanner scanner = new Scanner(System.in);
            while (true){
                displayMenuHeader("WELCOME TO FOODIE APP");
                System.out.println();
                System.out.println("Please select the option..!");
                System.out.println("---------------------------");
                System.out.println("1. Customer Section");
                System.out.println("2. Restaurant Section");
                System.out.println("3. Dishes Section");
                System.out.println("4. Order Section");
                System.out.println("5. Exit");
                System.out.println("Please Enter Your Choice 1 - 5");

                int input = scanner.nextInt();
                switch (input){
                    case 1 -> new CustomerMenu().displayMenu();
//                    case 2 -> new RestaurantMenu().displayMenu();
//                    case 3 -> new DishMenu().displayMenu();
//                    case 4 -> new OrderMenu().displayMenu();
                    case 5 -> {
                        System.out.println("Thanks for choosing Foodie App,See you again..!");
                    }
                    default -> System.out.println("Invalid Input.Please enter valid input from 1 - 5");
                }
            }
        } catch (Exception e){
            System.out.println("Some Internal Error Occurred. Please try Again..!");
            e.printStackTrace();
            displayMenu();
        }
    }
}
