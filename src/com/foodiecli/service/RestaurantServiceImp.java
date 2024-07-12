package com.foodiecli.service;

import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.exceptions.RestaurantExistsException;
import com.foodiecli.exceptions.RestaurantNotFoundException;
import com.foodiecli.factory.Factory;
import com.foodiecli.model.Dish;
import com.foodiecli.model.Restaurant;
import com.foodiecli.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantServiceImp implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImp(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getRestaurantList() {
        return this.restaurantRepository.restaurantList();
    }

    @Override
    public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(id);
        if (restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with the Id :" + id);
        return restaurantById.get();
    }

    @Override
    public Restaurant save(Restaurant restaurant) throws RestaurantExistsException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(restaurant.getRestaurantId());
        if (restaurantById.isPresent())
            throw new RestaurantExistsException("Restaurant Not Found with the Id :" + restaurant.getRestaurantId());
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(restaurant.getRestaurantId());
        if (restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with the Id :" + restaurant.getRestaurantId());
        return this.restaurantRepository.updateRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurant(String id) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(id);
        if (restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with the Id :" + id);
        this.restaurantRepository.deleteRestaurant(restaurantById.get());
    }

    @Override
    public List<Dish> getDishItem(String id) throws RestaurantNotFoundException, DishNotFoundException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.findRestaurantById(id);
        if (restaurantById.isEmpty())
            throw new RestaurantNotFoundException("Restaurant Not Found with the Id :" + id);
        List<Dish> dishList = new ArrayList<>();
        Restaurant restaurant = restaurantById.get();
        List<String> dishIds = restaurant.getMenu();
        DishService dishService = Factory.getDishService();
        for (String dishId : dishIds){
            Dish dish = dishService.getDishById(dishId);
            dishList.add(dish);
        }
        return dishList;
    }
}
