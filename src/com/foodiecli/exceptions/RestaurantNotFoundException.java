package com.foodiecli.exceptions;

public class RestaurantNotFoundException extends Exception{
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
