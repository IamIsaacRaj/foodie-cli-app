package com.foodiecli.ui;

import com.foodiecli.controller.RestaurantController;
import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.exceptions.RestaurantExistsException;
import com.foodiecli.exceptions.RestaurantNotFoundException;
import com.foodiecli.factory.Factory;
import com.foodiecli.model.Dish;
import com.foodiecli.model.Restaurant;
import com.foodiecli.service.RestaurantService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RestaurantMenu extends Menu{
    private final RestaurantController restaurantController;

    public RestaurantMenu() {
        this.restaurantController = Factory.getRestaurantController();
    }

    @Override
    public void displayMenu() {
       try {
           Scanner scanner = new Scanner(System.in);
           while (true){
               displayMenuHeader("WELCOME TO RESTAURANT SECTION");
               System.out.println();
               System.out.println("Please Select the option...!");
               System.out.println("1. View All Restaurants");
               System.out.println("2. Add New Restaurant");
               System.out.println("3. Search Restaurant");
               System.out.println("4. Update Restaurant");
               System.out.println("5. Delete Restaurant");
               System.out.println("6. Exit");

               System.out.println("Please Enter Your Choice 1 - 6");
               int input = scanner.nextInt();

               switch (input){
                   case 1 -> displayRestaurants();
                   case 2 -> addNewRestaurantForm();
                   case 3 -> searchRestaurantForm();
                   case 4 -> updateRestaurantForm();
                   case 5 -> deleteRestaurantForm();
                   case 6 -> {
                       System.out.println("Thank You, See you again..!");
                       super.displayMenu();
                   }
                   default -> System.out.println("Invalid Input.Please Enter Your Input from 1 - 6");
               }
           }
       }catch (Exception e){
           System.out.println("Some internal error occurred. Please try again...!");
           displayMenu();
       }
    }

    public void displayRestaurantDetails(Restaurant restaurant){
        displayMenuHeader("Restaurant Details");
        System.out.printf("%-10s %-30s %-80s %-30s \n", "ID", "NAME", "ADDRESS", "MENU ITEMS");
        printDashLine();
        System.out.printf("%-10s %-30s %-80s %-30s \n", restaurant.getRestaurantId(),restaurant.getName(),restaurant.getAddress(),restaurant.getMenu());
    }

    public void displayRestaurants(){
        List<Restaurant> restaurantList = restaurantController.getRestaurantList();
        displayMenuHeader("Restaurant Details");
        System.out.printf("%-10s %-30s %-80s %-30s \n", "ID", "NAME", "ADDRESS", "MENU ITEMS");
        printDashLine();
        restaurantList.forEach(restaurant -> {
            System.out.printf("%-10s %-30s %-80s %-30s \n", restaurant.getRestaurantId(),restaurant.getName(),restaurant.getAddress(),restaurant.getMenu());
        });
    }

    public void displayMenuItems(String restaurantId) throws RestaurantNotFoundException, DishNotFoundException {
        displayMenuHeader("Dishes Menu Details");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        printDashLine();
        RestaurantService restaurantService = Factory.getRestaurantService();
        List<Dish> dishItems = restaurantService.getDishItem(restaurantId);
        for(Dish dish : dishItems){
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getDishId(), dish.getDishName(), dish.getDishDescription(), String.format("$%.2f", dish.getDishPrice()));
        }
    }

    public void addNewRestaurantForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Details to Add New Restaurant \n");
            System.out.println("Enter ID");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Address");
            String address = scanner.nextLine();
            System.out.println("Enter Menu Dishes Separated by ',' (D010,D001)");
            String menu = scanner.nextLine();

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(id)
                    .setRestaurantName(name)
                    .setRestaurantAddress(address)
                    .setRestaurantMenu(Arrays.asList(menu.split(",")));
            Restaurant savedRestaurant = restaurantController.save(restaurant);
            displayRestaurantDetails(savedRestaurant);
        }catch (RestaurantExistsException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some internal error occurred. Please try again...!");
            addNewRestaurantForm();
        }
    }

    public void searchRestaurantForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the details to search Restaurant");
            System.out.println("Enter ID");
            String id = scanner.nextLine();
            Restaurant restaurant = restaurantController.getRestaurantById(id);
            displayRestaurantDetails(restaurant);
        }catch (RestaurantNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    public void updateRestaurantForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the Details to Update the Restaurant Details");
            System.out.println("Enter Restaurant ID");
            String id = scanner.nextLine();
            System.out.println("Enter new Name");
            String name = scanner.nextLine();
            System.out.println("Enter new Address");
            String address = scanner.nextLine();
            System.out.println("Enter Menu Dishes Separated by ',' (D010,D001)");
            String menu = scanner.nextLine();

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(name)
                    .setRestaurantAddress(address)
                    .setRestaurantMenu(Arrays.asList(menu.split(",")));
            Restaurant updatedRestaurant = restaurantController.updateRestaurant(restaurant);
            System.out.println("Restaurant Updated Successfully");
            displayRestaurantDetails(updatedRestaurant);
        }catch (RestaurantNotFoundException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Some internal error occurred. Please try again...!");
            updateRestaurantForm();
        }
    }

    public void deleteRestaurantForm(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the details to Delete Restaurant");
            System.out.println("Enter ID");
            String id = scanner.nextLine();
            restaurantController.deleteRestaurant(id);
        }catch (RestaurantNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }
}
