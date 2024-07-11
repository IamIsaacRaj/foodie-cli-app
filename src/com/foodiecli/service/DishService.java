package com.foodiecli.service;

import com.foodiecli.exceptions.DishAlreadyExistsException;
import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.model.Dish;

import java.util.List;

public interface DishService {
    public List<Dish> getDishesList();

    public Dish save(Dish dish) throws DishAlreadyExistsException;

    public Dish getDishById(String id) throws DishNotFoundException;

    public Dish updateDish(Dish dish) throws DishNotFoundException;

    public void delete(String id) throws DishNotFoundException;
}
