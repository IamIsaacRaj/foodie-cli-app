package com.foodiecli.exceptions;

public class DishNotFoundException extends Exception{
    public DishNotFoundException(String message){
        super(message);
    }
}
