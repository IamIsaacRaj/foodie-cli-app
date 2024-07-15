package com.foodiecli.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private String id;
    private Customer customer;
    private Restaurant restaurant;
    private List<Dish> dishes;
    private double price;
    private LocalDate orderDate;

//  Constructor

//    public Order(String id, Customer customer, Restaurant restaurant, List<Dish> dishes, double price, Date orderDate) {
//        this.id = id;
//        this.customer = customer;
//        this.restaurant = restaurant;
//        this.dishes = dishes;
//        this.price = price;
//        this.orderDate = orderDate;
//    }

//  Getters and setters for the fields

    public String getOrderId() {
        return id;
    }

    public Order setOrderId(String id) {
        this.id = id;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Order setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public List<Dish> getOrderDishes() {
        return dishes;
    }

    public Order setOrderDishes(List<Dish> dishes) {
        this.dishes = dishes;
        return this;
    }

    public double getOrderPrice() {
        return price;
    }

    public Order setOrderPrice(double price) {
        this.price = price;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

//  Overriding equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return  Double.compare(price, order.price) == 0 &&
                Objects.equals(id, order.id) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(restaurant, order.restaurant) &&
                Objects.equals(dishes, order.dishes) &&
                Objects.equals(orderDate, order.orderDate);
    }

//  Overriding hashCode

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, restaurant, dishes, price, orderDate);
    }

//  Overriding toString

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                ", price=" + price +
                ", orderDate=" + orderDate +
                '}';
    }


}
