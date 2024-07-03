package com.foodiecli.model;

import java.util.Objects;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String password;

    //Constructor(No-Arg)
    public Customer() {
    }

    //Getters and Setters for fields


    public String getCustomerId() {
        return id;
    }

    public Customer setCustomerId(String id) {
        this.id = id;
        return this;
    }

    public String getCustomerName() {
        return name;
    }

    public Customer setCustomerName(String name) {
        this.name = name;
        return this;
    }

    public String getCustomerEmail() {
        return email;
    }

    public Customer setCustomerEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCustomerPassword() {
        return password;
    }

    public Customer setCustomerPassword(String password) {
        this.password = password;
        return this;
    }
//  Overriding equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return  Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

//  Overriding toString
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
