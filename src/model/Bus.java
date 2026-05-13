package model;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

public class Bus {

    // CONSTANTS

    private static final int MIN_ENGINE_CAPACITY = 300;

    private static final Pattern BUS_NAME_PATTERN =
            Pattern.compile("^[A-Za-z]+(To|_to_)[A-Za-z]+$");

    private static final Pattern VEHICLE_NUMBER_PATTERN =
            Pattern.compile("^[A-Z]{2}\\s\\d{2}\\s[A-Z]{1,2}\\s\\d{4}$");

    // INSTANCE VARIABLES

    private final String BUS_ID;

    private String busName;
    private String vehicleNumber;

    private int engineCapacity;
    private int ticketPrice;

    private int totalSeats;
    private int occupiedSeats;
    private int vacantSeats;

    private final Scanner scan;

    // CONSTRUCTOR

    public Bus(Scanner scan) {

        this.scan = scan;
        this.BUS_ID = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        System.out.println("\n========== BUS REGISTRATION ==========");

        registerBusName();
        registerVehicleNumber();
        registerEngineCapacity();
        registerTicketPrice();
        registerSeatCapacity();

        System.out.println("\n=====================================");
        System.out.println("[BUS REGISTERED SUCCESSFULLY]");
        System.out.println("Bus ID        : " + BUS_ID);
        System.out.println("Bus Name      : " + busName);
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("=====================================\n");
    }

    // REGISTRATION METHODS

    private void registerBusName() {

        while (true) {

            try {

                System.out.println(
                        "Enter Bus Name (Example: VmplToRct):");

                String inputName = scan.nextLine().trim();

                if (!BUS_NAME_PATTERN.matcher(inputName).matches()) {
                    throw new IllegalArgumentException(
                            "Bus name format invalid.");
                }

                this.busName = inputName;
                break;

            } catch (IllegalArgumentException e) {
                System.out.println("[INVALID BUS NAME] " + e.getMessage());
            }
        }
    }

    private void registerVehicleNumber() {

        while (true) {

            try {

                System.out.println(
                        "Enter Vehicle Number (Example: KA 01 AB 1234):");

                String inputVehicle = scan.nextLine().trim().toUpperCase();

                if (!VEHICLE_NUMBER_PATTERN.matcher(inputVehicle).matches()) {
                    throw new IllegalArgumentException(
                            "Vehicle number format invalid.");
                }

                this.vehicleNumber = inputVehicle;
                break;

            } catch (IllegalArgumentException e) {
                System.out.println("[INVALID VEHICLE NUMBER] " + e.getMessage());
            }
        }
    }

    private void registerEngineCapacity() {

        while (true) {

            try {

                System.out.print("Enter Engine Capacity (Minimum 300cc): ");

                int inputCapacity = Integer.parseInt(
                        scan.nextLine().trim());

                if (inputCapacity < MIN_ENGINE_CAPACITY) {
                    throw new IllegalArgumentException(
                            "Engine capacity too low.");
                }

                this.engineCapacity = inputCapacity;
                break;

            } catch (NumberFormatException e) {
                System.out.println(
                        "[INVALID INPUT] Only numerical values allowed.");

            } catch (IllegalArgumentException e) {
                System.out.println(
                        "[INVALID ENGINE CAPACITY] " + e.getMessage());
            }
        }
    }

    private void registerTicketPrice() {

        while (true) {

            try {

                System.out.print("Enter Ticket Price: ");

                int inputPrice = Integer.parseInt(
                        scan.nextLine().trim());

                if (inputPrice <= 0) {
                    throw new IllegalArgumentException(
                            "Ticket price must be greater than zero.");
                }

                this.ticketPrice = inputPrice;
                break;

            } catch (NumberFormatException e) {
                System.out.println(
                        "[INVALID INPUT] Only numerical values allowed.");

            } catch (IllegalArgumentException e) {
                System.out.println(
                        "[INVALID PRICE] " + e.getMessage());
            }
        }
    }

    private void registerSeatCapacity() {

        while (true) {

            try {

                System.out.print("Enter Total Seat Capacity: ");

                int inputSeats = Integer.parseInt(
                        scan.nextLine().trim());

                if (inputSeats <= 0) {
                    throw new IllegalArgumentException(
                            "Seat count must be greater than zero.");
                }

                this.totalSeats = inputSeats;
                this.vacantSeats = inputSeats;
                this.occupiedSeats = 0;

                break;

            } catch (NumberFormatException e) {
                System.out.println(
                        "[INVALID INPUT] Only numerical values allowed.");

            } catch (IllegalArgumentException e) {
                System.out.println(
                        "[INVALID SEAT COUNT] " + e.getMessage());
            }
        }
    }

    // BOOKING LOGIC

    public boolean bookSeat(int requestedSeats) {

        if (requestedSeats <= 0) {
            System.out.println(
                    "[INVALID REQUEST] Seat count must be greater than zero.");
            return false;
        }

        if (requestedSeats > vacantSeats) {
            System.out.println(
                    "[ALERT] Only " + vacantSeats + " seats are available.");
            return false;
        }

        int totalAmount = requestedSeats * ticketPrice;

        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Bus Name     : " + busName);
        System.out.println("Requested    : " + requestedSeats + " seats");
        System.out.println("Ticket Price : " + ticketPrice);
        System.out.println("Total Amount : " + totalAmount);
        System.out.println("=====================================");

        if (!processPayment(totalAmount)) {
            System.out.println(
                    "[BOOKING FAILED] Payment was unsuccessful.");
            return false;
        }

        occupiedSeats += requestedSeats;
        vacantSeats -= requestedSeats;

        System.out.println("\n[BOOKING CONFIRMED]");
        System.out.println("Remaining Vacant Seats: " + vacantSeats);

        return true;
    }

    // PAYMENT LOGIC

    private boolean processPayment(int amount) {

        System.out.println("\n======= SECURE PAYMENT GATEWAY =======");
        System.out.println("Amount To Pay: " + amount + " INR");
        System.out.print("Enter Payment Amount: ");

        try {

            int paidAmount = Integer.parseInt(
                    scan.nextLine().trim());

            if (paidAmount < amount) {

                System.out.println(
                        "[PAYMENT FAILED] Remaining Amount: "
                                + (amount - paidAmount));

                return false;
            }

            if (paidAmount > amount) {

                int refund = paidAmount - amount;

                System.out.println(
                        "[PAYMENT SUCCESSFUL]");

                System.out.println(
                        "Refund Amount " + refund
                                + " INR will be processed within 3 business days.");

                return true;
            }

            System.out.println(
                    "[PAYMENT SUCCESSFUL] Exact amount received.");

            return true;

        } catch (NumberFormatException e) {

            System.out.println(
                    "[INVALID PAYMENT INPUT] Only numerical values allowed.");

            return false;
        }
    }

    // DISPLAY METHODS

    public void displayAvailableSeats() {
        System.out.println("Available Seats: " + vacantSeats);
    }

    public void displayReservedSeats() {
        System.out.println("Reserved Seats: " + occupiedSeats);
    }

    // GETTERS

    public String getBusId() {
        return BUS_ID;
    }

    public String getBusName() {
        return busName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public int getVacantSeats() {
        return vacantSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    // TOSTRING

    @Override
    public String toString() {

        return "Bus{" +
                "BUS_ID='" + BUS_ID + '\'' +
                ", busName='" + busName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", ticketPrice=" + ticketPrice +
                ", totalSeats=" + totalSeats +
                ", vacantSeats=" + vacantSeats +
                '}';
    }
}
