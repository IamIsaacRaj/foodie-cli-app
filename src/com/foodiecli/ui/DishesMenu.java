package com.foodiecli.ui;

import com.foodiecli.controller.DishController;
import com.foodiecli.exceptions.DishAlreadyExistsException;
import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.factory.Factory;
import com.foodiecli.model.Dish;

import java.util.List;
import java.util.Scanner;

public class DishesMenu extends Menu{
    private final DishController dishController;

    public DishesMenu() {
        this.dishController = Factory.getDishController();
    }

    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO DISHES SECTION");
                System.out.println();
                System.out.println("Please select the option..!");
                System.out.println("---------------------------");
                System.out.println("1. Add New Dish");
                System.out.println("2. View All Dish Items)");
                System.out.println("3. Search Dish");
                System.out.println("4. Update Dish");
                System.out.println("5. Delete Dish");
                System.out.println("6. Exit");
                System.out.println("Please Enter Your Choice 1 - 6");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> newDishForm();
                    case 2 -> displayDishes();
                    case 3 -> dishSearchForm();
                    case 4 -> dishUpdateForm();
                    case 5 -> dishDeleteForm();
                    case 6 -> {
                        System.out.println("Thanks You.See you again..!");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid Input.Please enter valid input from 1 - 6");
                }
            }
        }catch (Exception e){
            System.out.println("Some Internal Error Occurred. Please try Again..!");
            displayMenu();
        }
    }

    public void displayDishDetails(Dish dish){
        displayMenuHeader("Dish Details");
        System.out.printf("%-10s %-30s %-80s %-30s \n", "ID", "NAME", "DESCRIPTION", "PRICE");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-30s \n", dish.getDishId(), dish.getDishName(),dish.getDishDescription(), dish.getDishPrice());
    }

    public void displayDishes(){
        List<Dish> dishList = this.dishController.getDishesList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Dishes Details");
        System.out.printf("%-10s %-30s %-80s %-10s \n", "ID", "NAME", "E-MAIL", "PASSWORD");
        System.out.println(dashesLine);
        dishList.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s \n", dish.getDishId(), dish.getDishName(),dish.getDishDescription(), dish.getDishPrice());
        });

    }

    public void newDishForm(){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter the details to add new dish\n");
                System.out.println("Enter Id");
                String id = scanner.nextLine();
                System.out.println("Enter Name");
                String name = scanner.nextLine();
                System.out.println("Enter Description");
                String description = scanner.nextLine();
                System.out.println("Enter Price");
                double price = scanner.nextDouble();
                Dish dish = new Dish();
                dish.setDishId(id)
                        .setDishName(name)
                        .setDishDescription(description)
                        .setDishPrice(price);
                Dish savedDish = this.dishController.save(dish);
                System.out.println("New Dish Added Successful");
                displayDishDetails(savedDish);
        }catch (DishAlreadyExistsException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some Internal Error Occurred. Please try Again..!");
            newDishForm();
        }
    }

    public void dishSearchForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the Dish Id to Search Dish Details");
            System.out.println("Enter ID");
            String id = scanner.nextLine();
            Dish dish = dishController.getDishById(id);
            displayDishDetails(dish);
        }catch (DishNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void dishUpdateForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the details to update new dish\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setDishId(id)
                    .setDishName(name)
                    .setDishDescription(description)
                    .setDishPrice(price);
            Dish updatedDish = this.dishController.updateDish(dish);
            System.out.println("Dish Updated Successful");
            displayDishDetails(updatedDish);
        }catch (DishNotFoundException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some Internal Error Occurred. Please try Again..!");
            newDishForm();
        }
    }

    public void dishDeleteForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the Dish Id to Delete Dish Details");
            System.out.println("Enter ID");
            String id = scanner.nextLine();
            dishController.deleteDish(id);
        }catch (DishNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }
}
