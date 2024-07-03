package com.foodiecli;

import com.foodiecli.model.Customer;
import com.foodiecli.model.Dish;
import com.foodiecli.model.Restaurant;
import com.foodiecli.util.CsvReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CsvReader reader = new CsvReader();

        List<Customer> customers = reader.readCustomerFromCSV();
        customers.forEach(System.out::println);

        List<Restaurant> restaurants= reader.readRestaurantsFromCSV();
        restaurants.forEach(System.out::println);

        List<Dish> dishes = reader.readDishesFromCsv();
        dishes.forEach(System.out::println);
    }
}
