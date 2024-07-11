package com.foodiecli.repository;

import com.foodiecli.model.Restaurant;

import java.util.List;
import java.util.Optional;

public class RestaurantRepository {
    private List<Restaurant> restaurantList;

    public List<Restaurant> restaurantList() {
        return this.restaurantList;
    }

    public Restaurant save(Restaurant restaurant){
        this.restaurantList.add(restaurant);
        return restaurant;
    }

    public Optional<Restaurant> findRestaurantById(String id){
        return this.restaurantList.stream()
                .filter(restaurant -> restaurant.getRestaurantId().equals(id))
                .findFirst();
    }

    public Optional<Restaurant> updateRestaurant(Restaurant restaurantToBeUpdated){
        return this.restaurantList.stream()
                .filter(restaurant -> restaurant.getRestaurantId().equals(restaurantToBeUpdated.getRestaurantId()))
                .findFirst()
                .map(restaurant -> {
                    restaurant.setRestaurantName(restaurantToBeUpdated.getName())
                            .setRestaurantMenu(restaurantToBeUpdated.getMenu())
                            .setRestaurantAddress(restaurantToBeUpdated.getAddress());
                    return restaurant;
                });
    }

    public  void deleteRestaurant(Restaurant restaurant){
        this.restaurantList.remove(restaurant);
    }
}
