# Application Description

This Java and XML application implements functionalities for managing employees, customers, and orders. The application uses data modeling to represent the entities of the system and offers various functionalities, such as:

## Features

### Employee Management
- Register new employees
- Consult registered employees
- Update employee data
- Delete employees

### Customer Management
- Register new customers (after employee login)
- Consult registered customers
- Update customer data
- Delete customers (with checking linked orders)

### Order Management
- Register new orders (after employee login)
- Consult registered orders
- Update order data
- Delete orders

### Authentication
- Employee login

## Data Modeling

### Employee
- `funcId`: Unique employee identifier (int)
- `name`: Employee name (String)
- `email`: Employee email (String)
- `phone`: Employee phone number (String)
- `password`: Employee password (String)

### Customer
- `customerId`: Unique customer identifier (int)
- `name`: Customer name (String)
- `phone`: Customer phone number (String)

### Order
- `orderId`: Unique order identifier (int)
- `orderDescription`: Order description (String)
- `totalAmount`: Total order amount (double)
- `customerId`: Identifier of the customer who placed the order (int) (foreign key)

## List Views

### Customer List
- Displays the name and phone number of all registered customers.

### Order List
- Displays the order description, total amount, and name of the customer who placed the order.

## Business Rules
- Deleting a customer is only allowed if there are no orders linked to it.

## Implementation

The application is implemented in Java and uses XML for data storage.

## Requirements
- Java Development Kit (JDK)
- Text editor or Java IDE

## Installation
1. Clone the application repository.
2. Compile the Java project.
3. Run the Java application.

## Usage
- Follow the on-screen instructions to use the application's features.

## Observations
- This is a basic example of a Java and XML application.
- The application can be expanded to include more features and resources.

## License

This application is licensed under the MIT license.
