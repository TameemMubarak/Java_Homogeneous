package main;

import java.util.Scanner;

import model.User;
import service.AuthService;
import service.NammaBusYatra;

public class TicketBooking {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // SERVICES

        AuthService authService =
                new AuthService();

        NammaBusYatra system =
                new NammaBusYatra(scan);

        boolean applicationExit = false;

        while (!applicationExit) {

            System.out.println(
                    "\n========= NAMMA YATRA =========");

            System.out.println(
                    "1. Register");

            System.out.println(
                    "2. Login");

            System.out.println(
                    "3. Exit");

            System.out.print(
                    "Select Option: ");

            try {

                int authChoice =
                        Integer.parseInt(
                                scan.nextLine());

                switch (authChoice) {

                    case 1:

                        authService.register(scan);

                        break;

                    case 2:

                        User currentUser =
                                authService.login(scan);

                        if (currentUser != null) {

                            boolean logout = false;

                            while (!logout) {

                                System.out.println(
                                        "\n========= NAMMA YATRA MENU =========");

                                System.out.println(
                                        "1. View and Book Bus");

                                System.out.println(
                                        "2. Register New Bus");

                                System.out.println(
                                        "3. View Profile");

                                System.out.println(
                                        "4. Logout");

                                System.out.print(
                                        "Select Option: ");

                                try {

                                    int menuChoice =
                                            Integer.parseInt(
                                                    scan.nextLine());

                                    switch (menuChoice) {

                                        case 1:

                                            system.startBookingFlow();

                                            break;

                                        case 2:

                                            system.createBus(currentUser);

                                            break;

                                        case 3:

                                            currentUser.displayUserDetails();

                                            break;

                                        case 4:

                                            authService.logout();

                                            logout = true;

                                            break;

                                        default:

                                            System.out.println(
                                                    "[ERROR] Invalid menu option.");
                                    }

                                } catch (Exception e) {

                                    System.out.println(
                                            "[ERROR] Invalid input.");
                                }
                            }
                        }

                        break;

                    case 3:

                        System.out.println(
                                "\nThank you for using Namma Yatra.");

                        applicationExit = true;

                        break;

                    default:

                        System.out.println(
                                "[ERROR] Invalid option.");
                }

            } catch (Exception e) {

                System.out.println(
                        "[ERROR] Please enter a valid number.");
            }
        }

        scan.close();
    }
}