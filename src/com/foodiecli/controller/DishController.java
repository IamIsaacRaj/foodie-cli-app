package com.foodiecli.controller;

import com.foodiecli.exceptions.DishAlreadyExistsException;
import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.model.Dish;
import com.foodiecli.service.DishServiceImp;

import java.util.List;

public class DishController {
    private final DishServiceImp dishService;

    public DishController(DishServiceImp dishService) {
        this.dishService = dishService;
    }

    public List<Dish> getDishesList(){
        return this.dishService.getDishesList();
    }

    public Dish save(Dish dish) throws DishAlreadyExistsException{
        return this.dishService.save(dish);
    }

    public Dish getDishById(String id) throws DishNotFoundException{
        return this.dishService.getDishById(id);
    }

    public Dish updateDish(Dish dish) throws DishNotFoundException{
        return this.dishService.updateDish(dish);
    }

    public void deleteDish(String id) throws DishNotFoundException{
        this.dishService.deleteDish(id);
    }
}
