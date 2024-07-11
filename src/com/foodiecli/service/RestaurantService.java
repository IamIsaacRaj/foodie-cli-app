package com.foodiecli.service;

import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.exceptions.RestaurantExistsException;
import com.foodiecli.exceptions.RestaurantNotFoundException;
import com.foodiecli.model.Dish;
import com.foodiecli.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> getRestaurantList();

    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException;

    public Restaurant save(Restaurant restaurant) throws RestaurantExistsException;

    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;

    public  void deleteRestaurant(String id) throws RestaurantNotFoundException;

    public List<Dish> getDishItem(String id) throws RestaurantNotFoundException, DishNotFoundException;
}
