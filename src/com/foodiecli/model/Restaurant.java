package com.foodiecli.model;

import java.util.List;
import java.util.Objects;

public class Restaurant {
    private String id;
    private String name;
    private String address;
    private List<String> menu;

//  Constructor
    public Restaurant() {
    }

//  Getters and setters for the fields

    public String getId() {
        return id;
    }

    public Restaurant setRestaurantId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Restaurant setRestaurantName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Restaurant setRestaurantAddress(String address) {
        this.address = address;
        return this;
    }

    public List<String> getMenu() {
        return menu;
    }

    public Restaurant setRestaurantMenu(List<String> menu) {
        this.menu = menu;
        return this;
    }

//  Overriding equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(menu, that.menu);
    }

//  Overriding hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, menu);
    }

//  Overriding toString

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", menu=" + menu +
                '}';
    }
}
