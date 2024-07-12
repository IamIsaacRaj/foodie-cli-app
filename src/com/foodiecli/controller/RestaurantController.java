package com.foodiecli.controller;

import com.foodiecli.exceptions.RestaurantExistsException;
import com.foodiecli.exceptions.RestaurantNotFoundException;
import com.foodiecli.model.Restaurant;
import com.foodiecli.service.RestaurantServiceImp;

import java.util.List;

public class RestaurantController {
    private final RestaurantServiceImp restaurantService;

    public RestaurantController(RestaurantServiceImp restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Restaurant> getRestaurantList(){
        return this.restaurantService.getRestaurantList();
    }

    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException{
        return this.restaurantService.getRestaurantById(id);
    }

    public Restaurant save(Restaurant restaurant) throws RestaurantExistsException{
        return this.restaurantService.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException{
        return this.restaurantService.updateRestaurant(restaurant);
    }

    public  void deleteRestaurant(String id) throws RestaurantNotFoundException{
        this.restaurantService.deleteRestaurant(id);
    }
}
