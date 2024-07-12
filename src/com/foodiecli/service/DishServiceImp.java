package com.foodiecli.service;

import com.foodiecli.exceptions.DishAlreadyExistsException;
import com.foodiecli.exceptions.DishNotFoundException;
import com.foodiecli.model.Dish;
import com.foodiecli.repository.DishRepository;

import java.util.List;
import java.util.Optional;

public class DishServiceImp implements DishService{
    private final DishRepository dishRepository;

    public DishServiceImp(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> getDishesList() {
        return this.dishRepository.getDishList();
    }

    @Override
    public Dish save(Dish dish) throws DishAlreadyExistsException {
        Optional<Dish> getDishById = this.dishRepository.findDishById(dish.getDishId());
        if(getDishById.isPresent())
            throw new DishAlreadyExistsException("Dish Already Exists with this Id : " + dish.getDishId());

        return this.dishRepository.saveDish(dish);
    }

    @Override
    public Dish getDishById(String id) throws DishNotFoundException {
        Optional<Dish> getDishById = this.dishRepository.findDishById(id);
        if(getDishById.isEmpty())
            throw new DishNotFoundException("Dish Not Found with the Id : " + id);

        return getDishById.get();
    }

    @Override
    public Dish updateDish(Dish dish) throws DishNotFoundException {
        Optional<Dish> getDishById = this.dishRepository.findDishById(dish.getDishId());
        if(getDishById.isEmpty())
            throw new DishNotFoundException("Dish Not Found with the Id : " + dish.getDishId());
        return this.dishRepository.updateDish(dish);
    }

    @Override
    public void delete(String id) throws DishNotFoundException {
        Optional<Dish> dishById = this.dishRepository.findDishById(id);
        if(dishById.isEmpty())
            throw new DishNotFoundException("Dish Not Found with the Id : " + id);
        this.dishRepository.deleteDish(dishById.get());
    }
}
