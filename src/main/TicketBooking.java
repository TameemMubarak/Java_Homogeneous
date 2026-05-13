package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.User;
import service.NammaBusYatra;

public class TicketBooking {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // SYSTEM INITIALIZATION

        NammaBusYatra system =
                new NammaBusYatra(scan);

        User currentUser =
                new User(scan);

        boolean exit = false;

        while (!exit) {

            System.out.println(
                    "\n========= NAMMA YATRA MENU =========");

            System.out.println(
                    "1. View and Book a Bus");

            System.out.println(
                    "2. [ADMIN] Register a New Bus");

            System.out.println(
                    "3. Exit System");

            System.out.print(
                    "Please select an option: ");

            try {

                int choice =
                        Integer.parseInt(
                                scan.nextLine());

                switch (choice) {

                    case 1:

                        system.startBookingFlow();

                        break;

                    case 2:

                        system.createBus(currentUser);

                        break;

                    case 3:

                        System.out.println(
                                "Thank you for using Namma Yatra. Goodbye!");

                        exit = true;

                        break;

                    default:

                        System.out.println(
                                "Invalid choice. Try again.");
                }

            } catch (Exception e) {

                System.out.println(
                        "Error: Please enter a valid number.");
            }
        }

        scan.close();
    }
}