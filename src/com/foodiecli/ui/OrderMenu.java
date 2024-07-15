package com.foodiecli.ui;

import com.foodiecli.controller.OrderController;
import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.exceptions.OrderExistsException;
import com.foodiecli.exceptions.OrderNotFoundException;
import com.foodiecli.exceptions.RestaurantNotFoundException;
import com.foodiecli.factory.Factory;
import com.foodiecli.model.Customer;
import com.foodiecli.model.Dish;
import com.foodiecli.model.Order;
import com.foodiecli.model.Restaurant;
import com.foodiecli.service.CustomerService;
import com.foodiecli.service.DishService;
import com.foodiecli.service.RestaurantService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderMenu extends Menu {
    private final OrderController orderController;

    public OrderMenu() {
        this.orderController = Factory.getOrderController();
    }

    @Override
    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO ORDER SECTION");
                System.out.println();
                System.out.println("Please Select the Option !");
                System.out.println("--------------------------");
                System.out.println("1. Place New Order");
                System.out.println("2. Search Order");
                System.out.println("3. View All Orders");
                System.out.println("4. Update Order");
                System.out.println("5. Delete Order");
                System.out.println("6. Exit");
                System.out.println("Please enter your choice (1-6)");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> newOrderForm();
                    case 2 -> searchOrderForm();
                    case 3 -> ordersList();
                    case 4 -> updateOrderForm();
                    case 5 -> deleteOrderForm();
                    case 6 -> {
                        System.out.println("Thank you , See you again !");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from (1-6)");
                }
            }
        } catch (Exception e) {
            System.out.println("Some Internal Error Occurred.Please try again...!");
            displayMenu();
        }
    }

    private void ordersList() {
        List<Order> orderList = this.orderController.getOrdersList();
        displayMenuHeader("All Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s \n", "ID", "CUSTOMER NAME", "RESTAURANT NAME", "ITEMS", "ORDER DATE", "PRICE");
        printDashLine();
        orderList.forEach(order -> {
            String dishNames = order.getOrderDishes().stream().map(Dish::getDishName).collect(Collectors.joining(","));
            System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n", order.getOrderId(), order.getCustomer(), order.getRestaurant(), dishNames, order.getOrderDate(), order.getOrderPrice());
        });
        System.out.println("\n\n");
    }

    private void displayOrderDetails(Order order) {
        String dishNames = order.getOrderDishes().stream().map(Dish::getDishName).collect(Collectors.joining(","));
        displayMenuHeader("Order Details");
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s \n", "ID", "CUSTOMER NAME", "RESTAURANT NAME", "ITEMS", "ORDER DATE", "PRICE");
        printDashLine();
        System.out.printf("%-10s %-20s %-30s %-60s %-20s %-10s\n\n", order.getOrderId(), order.getCustomer(), order.getRestaurant(), dishNames, order.getOrderDate(), order.getOrderPrice());
    }

    private void newOrderForm() throws DishNotFoundException {
        Customer loggedInCustomer = null;
        Restaurant restaurant = null;
        List<Dish> dishList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(System.in);
            CustomerService customerService = Factory.getCustomerService();
            RestaurantService restaurantService = Factory.getRestaurantService();
            DishService dishService = Factory.getDishService();

            loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            if (loggedInCustomer != null)
                System.out.println("Hello, " + loggedInCustomer.getCustomerName());
            while (loggedInCustomer == null){
                System.out.println("Please login to place an order");
                new CustomerMenu().customerLoginForm();
                loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            }

            System.out.println("Enter Order Id :");
            String id = scanner.nextLine();

            while (restaurant == null){
                new RestaurantMenu().displayRestaurants();
                printDashLine();
                System.out.println("Please Choose the Restaurant Id (Ex: R08 )");
                String restaurantId = scanner.nextLine();
                restaurant = restaurantService.getRestaurantById(restaurantId);
            }

            char addMoreItems = 'Y';
            while (addMoreItems == 'Y'){
                new RestaurantMenu().displayMenuItems(restaurant.getRestaurantId());
                printDashLine();
                System.out.println("Enter the Dish Id (Ex D001)");
                String dishId = scanner.nextLine();
                Dish selectedDish = dishService.getDishById(dishId);
                dishList.add(selectedDish);
                System.out.println("Dish is added SuccessFully : " + selectedDish.getDishName());
                System.out.println("Do you Want to add more dishes (Y/N) :");
                addMoreItems = scanner.nextLine().charAt(0);
            }

            double orderPrice = calculateOrderTotalPrice(dishList);
            LocalDate orderDate = LocalDate.now();

            Order order = new Order();
            order.setOrderId(id)
                    .setCustomer(loggedInCustomer)
                    .setRestaurant(restaurant)
                    .setOrderDishes(dishList)
                    .setOrderPrice(orderPrice)
                    .setOrderDate(orderDate);

            Order placedOrder = orderController.saveOrder(order);
            if (placedOrder != null)
                System.out.println("Order Placed Successfully with the following Details");

            displayOrderDetails(placedOrder);

        } catch (RestaurantNotFoundException | OrderExistsException e){
            System.out.println(e.getMessage());
        }

    }

    private double calculateOrderTotalPrice(List<Dish> dishList){
        return dishList.stream().mapToDouble(Dish::getDishPrice).sum();
    }

    private void searchOrderForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter the Order Details To search for Order \n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            Order order = orderController.getOrderById(id);
            displayOrderDetails(order);
        }catch (OrderNotFoundException e){
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    private void updateOrderForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Order Details to Update Order");
            System.out.println("Enter Order Id");
            String orderId = scanner.nextLine();

            Order existingOrder = orderController.getOrderById(orderId);

            RestaurantService restaurantService = Factory.getRestaurantService();
            DishService dishService = Factory.getDishService();

            Restaurant restaurant = existingOrder.getRestaurant();
            List<Dish> dishList = existingOrder.getOrderDishes();

            char updateItems = 'N';
            System.out.println("Do you want to change Restaurant? (Y/N)");
            char changeRestaurant = scanner.nextLine().charAt(0);

            if (changeRestaurant == 'Y'){
                new RestaurantMenu().displayRestaurants();
                printDashLine();
                System.out.println("Choose Restaurant Id (Ex R009)");
                String restaurantId = scanner.nextLine();
                restaurant = restaurantService.getRestaurantById(restaurantId);
                updateItems = 'Y';
            }

            if (updateItems == 'Y'){
                dishList = new ArrayList<>();
                char addMoreItems = 'Y';
                while (addMoreItems == 'Y'){
                    new RestaurantMenu().displayMenuItems(restaurant.getRestaurantId());
                    printDashLine();
                    System.out.println("Enter the Dish Id : ");
                    String dishId = scanner.nextLine();
                    Dish selectedDish = dishService.getDishById(dishId);
                    dishList.add(selectedDish);
                    System.out.println("One Dish is added successfully : " + selectedDish.getDishName());
                    System.out.println("Do you want to add more dishes (Y/N)");
                    addMoreItems = scanner.nextLine().charAt(0);

                    double orderPrice = calculateOrderTotalPrice(dishList);
                    LocalDate date = LocalDate.now();
                    existingOrder.setRestaurant(restaurant)
                                 .setOrderDishes(dishList)
                                 .setOrderPrice(orderPrice)
                                 .setOrderDate(date);
                    Order updatedOrder = orderController.updateOrder(existingOrder);

                    if (updatedOrder != null)
                        System.out.println("Order Updated Successfully with the following Details");

                    displayOrderDetails(updatedOrder);
                }
            }

        } catch (OrderNotFoundException | RestaurantNotFoundException | DishNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteOrderForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Order Details to Delete Order");
            System.out.println("Enter Order Id : ");
            String id = scanner.nextLine();
            this.orderController.deleteOrder(id);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
