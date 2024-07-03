package com.foodiecli.util;

import com.foodiecli.model.Customer;
import com.foodiecli.model.Dish;
import com.foodiecli.model.Restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {
    private List<Dish> dishList;
    private String CUSTOMERS_FILE = "D:\\foodie-cli-java-app\\data\\customers.csv";
    private String RESTAURANTS_FILE = "D:\\foodie-cli-java-app\\data\\restaurants.csv" ;
    private String DISHES_FILE = "D:\\foodie-cli-java-app\\data\\dishes.csv";

    public List<Dish> readDishesFromCsv(){
        String line;
        List<Dish> dishList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(DISHES_FILE))){
            String CSVSplitBy = ",";
            br.readLine();// Skip header
            while((line = br.readLine()) != null) {
                String[] data = line.split(CSVSplitBy);
                Dish dish = new Dish();
                dish.setDishId(data[0]);
                dish.setDishName(data[1]);
                dish.setDishDescription(data[2]);
                dish.setDishPrice(Double.parseDouble(data[3]));
                dishList.add(dish);
            }
            this.dishList = dishList;

        }catch (IOException e) {

            System.out.println("Issues in the CSV file from the path : " + CUSTOMERS_FILE);
            System.exit(0);
        }
        return dishList;
    }

    public List<Customer> readCustomerFromCSV() {
        List<Customer> customerList = new ArrayList<>();
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String CSVSplitBy = ",";
            br.readLine();// Skip header
            while ((line = br.readLine()) != null){
                String[] data = line.split(CSVSplitBy);
                Customer customer = new Customer();
                customer.setCustomerId(data[0]);
                customer.setCustomerName(data[1]);
                customer.setCustomerEmail(data[2]);
                customer.setCustomerPassword(data[3]);
                customerList.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Issues in the CSV file from the path : " + CUSTOMERS_FILE);
            System.exit(0);
        }
        return customerList;
    }

    public List<Restaurant> readRestaurantsFromCSV(){
        List<Restaurant> restaurantList = new ArrayList<>();
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(RESTAURANTS_FILE))) {
            String CSVSplitBy = ",";
            br.readLine();// Skip header
            while ((line = br.readLine()) != null){
                String[] data = line.split(CSVSplitBy);
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(data[0]);
                restaurant.setRestaurantName(data[1]);
                restaurant.setRestaurantAddress(data[2]);
                restaurant.setRestaurantMenu(Arrays.asList(data[3].split(":")));
                restaurantList.add(restaurant);
            }
        }  catch (IOException e) {
            e.printStackTrace();
            System.out.println("Issues in the CSV file from the path: " + RESTAURANTS_FILE);
            System.exit(0);
        }
        return restaurantList;
    }
}
