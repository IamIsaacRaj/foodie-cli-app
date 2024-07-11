package com.foodiecli.repository;

import com.foodiecli.factory.Factory;
import com.foodiecli.model.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishRepository {
    List<Dish> dishList;

    public DishRepository(){
        this.dishList = Factory.getCsvReader().readDishesFromCsv();
    }

    public List<Dish> getDishList(){
        return this.dishList;
    }

    public  Dish saveDish(Dish dish){
        this.dishList.add(dish);
        return dish;
    }

    public Optional<Dish> getDishById(String id){
        return this.dishList.stream()
                            .filter(dish -> dish.getDishId().equals(id))
                            .findFirst();
    }

    public Optional<Dish> updateDish(Dish dishToBeUpdated){
        return this.dishList.stream()
                            .filter(dish -> dish.getDishId().equals(dishToBeUpdated.getDishId()))
                            .findFirst()
                            .map(dish -> {
                               dish.setDishName(dishToBeUpdated.getDishName())
                                   .setDishDescription(dishToBeUpdated.getDishDescription())
                                   .setDishPrice(dishToBeUpdated.getDishPrice());
                               return dish;
                            });
    }

    public void deleteDish(Dish dish){
        this.dishList.remove(dish);
    }
}
