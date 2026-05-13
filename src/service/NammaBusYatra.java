package service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Bus;
import model.User;
import service.BusService;

public class NammaBusYatra {

    private static final List<Bus> busList =
            new ArrayList<>();

    private final Scanner scan;

    // SERVICE OBJECT

    private final BusService busService =
            new BusService();

    // CONSTRUCTOR

    public NammaBusYatra(Scanner scan) {
        this.scan = scan;
    }

    // CREATE BUS

    public void createBus(User user) {

        if (!user.getRole().equals("ADMIN")) {

            System.out.println(
                    "\n[DENIED] Only ADMIN users can register buses.");

            return;
        }

        try {

            System.out.println(
                    "\n========== BUS REGISTRATION ==========");

            // BUS NAME

            System.out.print(
                    "Enter Bus Name (Example: VmplToRct): ");

            String busName =
                    scan.nextLine().trim();

            // VEHICLE NUMBER

            System.out.print(
                    "Enter Vehicle Number (Example: KA 01 AB 1234): ");

            String vehicleNumber =
                    scan.nextLine().trim().toUpperCase();

            // ENGINE CAPACITY

            System.out.print(
                    "Enter Engine Capacity: ");

            int engineCapacity =
                    Integer.parseInt(
                            scan.nextLine().trim());

            // TICKET PRICE

            System.out.print(
                    "Enter Ticket Price: ");

            int ticketPrice =
                    Integer.parseInt(
                            scan.nextLine().trim());

            // TOTAL SEATS

            System.out.print(
                    "Enter Total Seats: ");

            int totalSeats =
                    Integer.parseInt(
                            scan.nextLine().trim());

            // CREATE BUS OBJECT

            Bus newBus = new Bus(
                    java.util.UUID.randomUUID()
                            .toString()
                            .substring(0, 8)
                            .toUpperCase(),

                    busName,

                    vehicleNumber,

                    engineCapacity,

                    ticketPrice,

                    totalSeats);

            busList.add(newBus);

            System.out.println(
                    "\n[SUCCESS] Bus added successfully.");

        } catch (NumberFormatException e) {

            System.out.println(
                    "[ERROR] Invalid numerical input.");

        } catch (Exception e) {

            System.out.println(
                    "[ERROR] Bus registration failed.");
        }
    }

    // DISPLAY BUSES

    public void showBuses() {

        if (busList.isEmpty()) {

            System.out.println(
                    "\n[INFO] No buses currently available.");

            return;
        }

        System.out.println(
                "\n========== ACTIVE ROUTES ==========");

        for (int i = 0; i < busList.size(); i++) {

            Bus currentBus = busList.get(i);

            System.out.println(
                    (i + 1)
                            + ". "
                            + currentBus.getBusName()
                            + " | "
                            + currentBus.getVehicleNumber());
        }

        System.out.println(
                "===================================");
    }

    // BOOKING FLOW

    public void startBookingFlow() {

        if (busList.isEmpty()) {

            System.out.println(
                    "\n[ERROR] No buses available for booking.");

            return;
        }

        showBuses();

        System.out.println(
                "Enter 0 to return to menu.");

        System.out.print(
                "Select Bus Number: ");

        try {

            int choice =
                    Integer.parseInt(
                            scan.nextLine().trim());

            if (choice == 0) {
                return;
            }

            int index = choice - 1;

            if (index < 0 || index >= busList.size()) {

                System.out.println(
                        "[ERROR] Invalid bus selection.");

                return;
            }

            Bus selectedBus =
                    busList.get(index);

            processBooking(selectedBus);

        } catch (Exception e) {

            System.out.println(
                    "[ERROR] Please enter a valid number.");
        }
    }

    // PROCESS BOOKING

    private void processBooking(Bus bus) {

        System.out.println(
                "\n========== BOOKING DETAILS ==========");

        System.out.println(
                "Bus Name : "
                        + bus.getBusName());

        bus.displayAvailableSeats();

        System.out.println(
                "=====================================");

        try {

            System.out.print(
                    "Enter Number Of Seats: ");

            int requestedSeats =
                    Integer.parseInt(
                            scan.nextLine().trim());

            if (requestedSeats <= 0) {

                System.out.println(
                        "[ERROR] Seat count must be greater than zero.");

                return;
            }

            boolean bookingStatus =
                    busService.bookSeat(
                            bus,
                            requestedSeats,
                            scan);

            if (bookingStatus) {

                System.out.println(
                        "\n[SUMMARY] Booking completed successfully.");

            } else {

                System.out.println(
                        "\n[SUMMARY] Booking failed.");
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "[ERROR] Invalid seat count input.");
        }
    }

    // GETTER

    public static List<Bus> getBusList() {
        return busList;
    }
}