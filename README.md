
# Foodie-cli Project

## Overview
The Foodie CLI Application is a Java-based command-line interface (CLI) application inspired by Zomato. It allows customers to choose restaurants, browse dishes, and place orders seamlessly through the terminal.

## Features
- **Customer Account Flow**: Customer can register and login.
- **Restaurant Selection**: Browse and select from a list of available restaurants.
- **Dish Browsing**: View the menu and detailed descriptions of dishes offered by each restaurant.
- **Order Placement**: Place orders for selected dishes from the chosen restaurant.
- **Order Summary**: View a summary of your order before finalizing it.

## Project Structure

The project is structured into several packages:
- `controller`: Contains classes for handling user input and interactions.
- `repository`: Manages data access and storage operations.
- `service`: Implements business logic and services.
- `model`: Defines data models used throughout the application.
- `util`: Utility classes and helper methods.
- `ui`: User interface components for displaying information to the user.
- `exceptions`: Custom exception classes for handling errors.

## Model Classes

### Customer
- Attributes: id, name, email, password
- Encapsulated with private fields
- Includes no-arg constructors, getters, setters
- Overridden hashCode(), equals(), and toString() methods

### Dishes
- Attributes: id, name, description, price
- Encapsulated with private fields
- Includes no-arg constructors, getters, setters
- Overridden hashCode(), equals(), and toString() methods

### Order
- Attributes: id, customer, restaurant, dish, price
- Encapsulated with private fields
- Includes no-arg constructors, getters, setters
- Overridden hashCode(), equals(), and toString() methods

### Restaurant
- Attributes: id, name, address, menu
- Encapsulated with private fields
- Includes no-arg constructors, getters, setters
- Overridden hashCode(), equals(), and toString() methods

## Data Storage

Data for customers, dish, and restaurants is stored in CSV format within the `data` folder.

## Installation
To install and run the Restaurant CLI Application, follow these steps:

1. **Clone the repository**
   ```bash
   git clone https://github.com/madhusamala-dev/foodie-cli-java.git
    ```
2. **CD into the application folder**
   ```bash
   cd foodie-cli-java
    ```
3. **Compile the application**
   ```bash
    javac -d bin src/*.java
    ```  
4. **Run the application**
   ```bash
    java -cp bin Main
    ``` 

**Usage**

Once the application is running, follow the on-screen prompts to navigate through the menu.

- **Register/Login**: Register as a new customer or login if you already have an account.
- **Browse Restaurants**: View the list of available restaurants.
- **View Dishes**: Select a restaurant to view its menu and dish descriptions.
- **Place an Order**: Choose dishes and place an order.
- **View Order History**: Access your past orders.