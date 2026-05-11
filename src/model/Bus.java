package model;

import java.util.*;
import java.util.regex.Pattern;

public class Bus {

    private String name;
    private String vehicleNumber;
    private int engineCC;
    private int price;
    private int seats;
    private int occupiedSeats = 0;
    private int vacantSeats;
    private Scanner scan;

    public void knowAvailableSeats() {
        System.out.println("No. of Vacant Seats: " + vacantSeats);
    }

    public void knowReservedSeats() {
        System.out.println("No. of Reserved Seats: " + occupiedSeats);
    }

    public String getName() {
        return this.name;
    }

    public Bus(Scanner scan) {
        this.scan = scan;
        System.out.println("\n===== Initializing New Bus Registration =====");

        while (true) {
            System.out.println(
                    "->please enter the bus name\n It should have format boradingpoint_to_destinantionport ==> Ex: vmplToRct.");
            String name = scan.nextLine().trim();

            if (Pattern.matches("^[a-zA-Z]+(To|_to_)[a-zA-Z]+$", name)) {
                this.name = name;
                break;
            }

            System.out.println("[ERROR] Invalid format! Use letters followed by 'To' or '_to_' and more letters.");
        }

        while (true) {
            System.out.println("-> Please enter the Vehicle Number (Format: AA 11 1111):");
            String inputVh = scan.nextLine().trim();
            if (Pattern.matches("^[A-Z]{2}\\s\\d{2}\\s\\d{4}$", inputVh)) {
                this.vehicleNumber = inputVh;
                break;
            }
            System.out.println("[ERROR] Invalid format! Ensure 2 Caps, Space, 2 Digits, Space, 4 Digits.");
        }

        while (true) {
            System.out.println("-> Enter the Engine Capacity (Minimum 300cc):");
            try {
                int inputCC = scan.nextInt();
                if (inputCC >= 300) {
                    this.engineCC = inputCC;
                    break;
                }
                System.out.println("[ERROR] Engine power too low! Must be 300cc or higher.");
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Please enter a valid number for CC.");
                scan.next();
            }
        }

        while (true) {
            System.out.println("-> Set the Ticket Price (Must be greater than 0):");
            try {
                int inputPrice = scan.nextInt();
                if (inputPrice > 0) {
                    this.price = inputPrice;
                    break;
                }
                System.out.println("[ERROR] Price cannot be zero or negative.");
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Please enter a valid number for Price.");
                scan.next();
            }
        }

        while (true) {
            System.out.println("-> Enter total Seats Available (Must be greater than 0):");
            try {
                int inputSeats = scan.nextInt();
                if (inputSeats > 0) {
                    this.seats = inputSeats;
                    this.vacantSeats = inputSeats;
                    break;
                }
                System.out.println("[ERROR] Seat capacity must be at least 1.");
            } catch (InputMismatchException e) {
                System.out.println("[ERROR] Please enter a valid number for Seats.");
                scan.next();
            }
        }

        scan.nextLine();
        System.out.println("\n[SUCCESS] Bus " + vehicleNumber + " is now ready for service!");
        System.out.println("==============================================\n");
    }

    public boolean bookSeat(int count) {
        int seatPrice = this.price;
        int totalCost = seatPrice * count;
        System.out.println("The total amount to be paid is " + totalCost + " to book " + count + " seats.");
        // 1. Check availability immediately to save user time
        if (vacantSeats < count) {
            System.out.println("\n[ALERT] All seats are occupied for this route.");
            return false;
        }

        // 2. Process Payment
        if (!payment(totalCost)) {
            System.out.println("[FAILED] Booking incomplete. Payment was not successful.");
            return false;
        }

        // 3. Update State
        occupiedSeats += count;
        vacantSeats -= count;

        System.out.println("\n[SUCCESS] Seat reserved successfully! -_-:)");
        System.out.println("Remaining Vacant Seats: " + vacantSeats);
        return true;
    }

    boolean payment(int price) {
        System.out.println("\n--- Secure Payment Gateway ---");
        System.out.println("Ticket Fare: " + price + " Rupees.");
        System.out.print("Enter amount to pay: ");

        try {
            int amountPaid = scan.nextInt();
            scan.nextLine(); // Critical: Clear buffer for next inputs

            if (amountPaid == price) {
                System.out.println("[PAID] Exact amount received. Thank you!");
                return true;
            } else if (amountPaid > price) {
                int refund = amountPaid - price;
                System.out.println("[PAID] Overpayment detected.");
                System.out.println("Refund of " + refund + " Rupees will be credited to your account within 3 days.");
                return true;
            } else {
                System.out.println("[DENIED] Insufficient amount. You still owe " + (price - amountPaid) + " Rupees.");
                return false;
            }
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Invalid input! Please enter a numerical value.");
            scan.nextLine(); // Clear the bad input
            return false;
        }
    }

}
