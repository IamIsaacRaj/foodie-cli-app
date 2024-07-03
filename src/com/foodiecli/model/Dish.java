package com.foodiecli.model;

import java.util.Objects;

public class Dish {
    private String id;
    private String name;
    private String description;
    private double price;

//  Constructor
    public Dish() {
    }

//  Getters and setters for the fields

    public String getDishId() {
        return id;
    }

    public Dish setDishId(String id) {
        this.id = id;
        return this;
    }

    public String getDishName() {
        return name;
    }

    public Dish setDishName(String name) {
        this.name = name;
        return this;
    }

    public String getDishDescription() {
        return description;
    }

    public Dish setDishDescription(String description) {
        this.description = description;
        return this;
    }

    public double getDishPrice() {
        return price;
    }

    public Dish setDishPrice(double price) {
        this.price = price;
        return this;
    }

//  Overriding equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return  Double.compare(price, dish.price) == 0 &&
                Objects.equals(id, dish.id) &&
                Objects.equals(name, dish.name) &&
                Objects.equals(description, dish.description);
    }

//  Overriding hashCode

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

//  Overriding toString

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
