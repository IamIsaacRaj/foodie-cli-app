package com.foodiecli.factory;

import com.foodiecli.controller.CustomerController;
import com.foodiecli.controller.DishController;
import com.foodiecli.controller.RestaurantController;
import com.foodiecli.repository.CustomerRepository;
import com.foodiecli.repository.DishRepository;
import com.foodiecli.repository.RestaurantRepository;
import com.foodiecli.service.CustomerServiceImp;
import com.foodiecli.service.DishServiceImp;
import com.foodiecli.service.RestaurantServiceImp;
import com.foodiecli.util.CsvReader;

public class Factory {

    public static CsvReader getCsvReader(){
        return Holder.CSV_READER;
    }

    public static CustomerRepository getCustomerRepository(){
        return Holder.CUSTOMER_REPOSITORY;
    }
    public static CustomerServiceImp getCustomerService() {
        return Holder.CUSTOMER_SERVICE;
    }

    public static CustomerController getCustomerController(){
        return Holder.CUSTOMER_CONTROLLER;
    }


    public static DishRepository getDishRepository(){
        return Holder.DISH_REPOSITORY;
    }
    public static DishServiceImp getDishService() {
        return Holder.DISH_SERVICE;
    }

    public static DishController getDishController(){
        return Holder.DISH_CONTROLLER;
    }

    public static RestaurantRepository getRestaurantRepository(){
        return Holder.RESTAURANT_REPOSITORY;
    }
    public static RestaurantServiceImp getRestaurantService() {
        return Holder.RESTAURANT_SERVICE;
    }

    public static RestaurantController getRestaurantController(){
        return Holder.RESTAURANT_CONTROLLER;
    }
    private static class Holder {
        private static final CsvReader CSV_READER = new CsvReader();

        private static final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepository();
        private static final CustomerServiceImp CUSTOMER_SERVICE = new CustomerServiceImp(CUSTOMER_REPOSITORY);
        private static final CustomerController CUSTOMER_CONTROLLER = new CustomerController(CUSTOMER_SERVICE);

        private static final DishRepository DISH_REPOSITORY = new DishRepository();
        private static final DishServiceImp DISH_SERVICE = new DishServiceImp(DISH_REPOSITORY);
        private static final DishController DISH_CONTROLLER = new DishController(DISH_SERVICE);

        private static final RestaurantRepository RESTAURANT_REPOSITORY = new RestaurantRepository();
        private static final RestaurantServiceImp RESTAURANT_SERVICE = new RestaurantServiceImp(RESTAURANT_REPOSITORY);
        private static final RestaurantController RESTAURANT_CONTROLLER = new RestaurantController(RESTAURANT_SERVICE);
    }
}

