# Java_Homogeneous

A centralized repository containing my Java projects developed while learning and practicing core Java concepts, object-oriented programming, problem solving, system design thinking, and backend-oriented logic without relying heavily on frameworks.

This repository mainly focuses on:
- Pure Java development
- Console-based application logic
- OOP implementation
- Data structure usage
- Exception handling
- Input validation
- Real-world workflow simulation
- Service-layer thinking
- Practical problem solving

The purpose of this repository is not only to store projects, but also to document my progression as a developer through incremental improvements, refactoring, debugging, and feature enhancement.

---

# Repository Philosophy

Most modern applications depend heavily on frameworks.  
This repository intentionally focuses on strengthening the foundation first.

Before abstracting logic behind frameworks, I aim to understand:
- how systems work internally,
- how logic flows,
- how state is managed,
- how validation is performed,
- how objects interact,
- and how scalable architecture begins from simple design principles.

The projects here are built primarily using:
- Core Java
- OOP concepts
- Java Collections
- Exception Handling
- Regex Validation
- File handling (future additions)
- Service-oriented structuring
- Basic software architecture practices

---

# Current Featured Project

# Namma Yatra - Console Based Bus Ticket Booking System

A Java-based console application that simulates a basic real-world bus reservation and management system.

This project was developed to practice:
- Object-Oriented Programming
- Workflow-based application design
- Role-based access control
- Input validation
- Exception handling
- Payment simulation
- Seat management logic
- Git and version control practices

---

# Features

## User Registration System
- User account creation
- Role selection:
  - Admin
  - Passenger
- Input handling and validation

---

## Admin Functionalities
- Register new buses into the system
- Add:
  - Bus Name
  - Vehicle Number
  - Engine Capacity
  - Ticket Price
  - Seat Capacity

Access restricted only to admin users.

---

## Passenger Functionalities
- View available buses
- Check available seats
- Book seats
- Payment simulation
- Booking confirmation

---

## Validation Features
Implemented using:
- Regular Expressions
- Exception Handling
- Defensive Programming

Examples:
- Vehicle number format validation
- Bus naming convention validation
- Numerical input validation
- Seat count validation
- Payment amount verification

---

# Technical Concepts Used

## Core Java
- Classes and Objects
- Constructors
- Encapsulation
- Method decomposition
- Static members
- Access modifiers

---

## Exception Handling
Used:
- try-catch blocks
- InputMismatchException
- Validation-based error handling

---

## OOP Principles
Implemented concepts such as:
- Abstraction
- Encapsulation
- Responsibility separation

---

## Collections Framework
Used:
- ArrayList

For:
- Managing buses dynamically
- Maintaining runtime data

---

## Regex Validation
Used Java Pattern Matching for:
- Vehicle Number Validation
- Route Naming Validation

---

# Sample Workflow

```text
1. User Registers
2. System Assigns Role
3. Admin Adds Bus
4. Passenger Views Available Routes
5. Passenger Selects Bus
6. Seat Availability Checked
7. Payment Processed
8. Booking Confirmed


Java_Homogeneous/
│
├── TicketBooking.java
│
├── Bus
├── User
├── Admin
├── NammaBusYatra
│
└── README.md