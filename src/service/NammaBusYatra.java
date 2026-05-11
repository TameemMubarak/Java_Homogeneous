package service;

import java.util.*;
import model.Bus;
import model.User;

public class NammaBusYatra {
    private static List<Bus> busList = new ArrayList<>();
    Scanner scan;

    public NammaBusYatra(Scanner scan) {
        this.scan = scan;
    }

    // To create a bus and add to the avialbale bus
    public void createBus(User user) {
        // For user authorization
        if (!user.getRole().equalsIgnoreCase("admin")) {
            System.out.println("\n[DENIED] Access Restricted: Only admins can register new buses.");
            return;
        }
        // This calls your Bus constructor which already handles its own internal
        // prompts
        Bus newBus = new Bus(scan);
        busList.add(newBus);
        System.out.println("[SYSTEM] Bus added to NammaYatra fleet successfully.");
    }

    // To display the buses available to travel.
    public void showBuses() {
        if (busList.isEmpty()) {
            System.out.println("\n[INFO] No buses are currently scheduled.");
            return;
        }

        System.out.println("\n---------- NammaYatra Active Routes ----------");
        for (int i = 0; i < busList.size(); i++) {
            System.out.println((i + 1) + ". " + busList.get(i).getName());
        }
        System.out.println("-----------------------------------------------");
    }

    // the method for booking ticket
    public void startBookingFlow() {
        if (busList.isEmpty()) {
            System.out.println("\n[ERROR] Cannot proceed. No buses available for booking.");
            return;
        }

        showBuses();
       
        System.out.println("To go back to MENU Enter 0.");
        System.out.print("Select the Bus Number you wish to book (e.g., 1): ");
       
        try {
            int choice = scan.nextInt();
            scan.nextLine(); // Clear buffer
            int index = choice - 1;
            if(index==-1)return;
            else if (index >= 0 && index < busList.size()) {
                Bus selectedBus = busList.get(index);
                processBooking(selectedBus);
            } else {
                System.out.println("[ERROR] Invalid selection. Please pick a number from the list.");
            }
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Please enter a valid numerical ID.");
            scan.nextLine(); // Clear buffer
        }
    }

    private void processBooking(Bus bus) {
        System.out.println("\n--- Booking Details for: " + bus.getName() + " ---");
        bus.knowAvailableSeats();

        System.out.print("How many seats would you like to book? ");
        try {
            int count = scan.nextInt();
            scan.nextLine(); // Clear buffer

            // Basic check before starting
            if (count <= 0) {
                System.out.println("[ERROR] Invalid number of seats.");
                return;
            }

            if (bus.bookSeat(count)) {
                System.out.println("\n[SUMMARY] Successfully booked " + count + " seat(s) on " + bus.getName());
            }

        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Please enter a numerical value for seat count.");
            scan.nextLine();
        }
    }

    public static List<Bus> getBusList() {
        return busList;
    }

}
