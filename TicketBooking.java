import java.util.*;
import java.util.regex.Pattern;

class nammaBusYatra {
    private static List<Bus> busList = new ArrayList<>();
    Scanner scan;

    nammaBusYatra(Scanner scan) {
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

class Bus {

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

    Bus(Scanner scan) {
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

    boolean bookSeat(int count) {
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

class User {
    private final String NAME;
    private final String pwd;
    private final long NUMBER;
    private final String EMAIL;
    private final String ROLE;

    public String getRole() {
        return this.ROLE;
    }

    User(Scanner scan) {
        String tempName = "", tempRole = "", tempPwd = "", tempEmail = "";
        long tempNumber = 0;

        while (true) {
            try {
                System.out.println("--- User Registration ---");

                System.out.print("Enter Full Name: ");

                tempName = scan.nextLine();

                System.out.print("Enter Role (Admin/Passenger): ");
                tempRole = scan.next();
                if (!(tempRole.equalsIgnoreCase("Admin") || tempRole.equalsIgnoreCase("Passenger"))) {
                    throw new Exception("Invalid Role! You must enter 'Admin' or 'Passenger'.");
                }

                System.out.print("Create Password: ");
                tempPwd = scan.next();

                System.out.print("Enter Email Address: ");
                tempEmail = scan.next();

                System.out.print("Enter Mobile Number: ");
                tempNumber = scan.nextLong();

                break;
            } catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
                scan.nextLine();
                System.out.println("Please restart the registration process.\n");
            }
        }

        this.NAME = tempName;
        this.ROLE = tempRole;
        this.pwd = tempPwd;
        this.EMAIL = tempEmail;
        this.NUMBER = tempNumber;

        System.out.println("\n[SUCCESS] Welcome, " + this.NAME + "!");
        System.out.println("Account created successfully as a " + this.ROLE + ".");
        System.out.println("----------------------------\n");
        System.out.println("Now please Log In again with your credentials ");
    }

}

class admin {
    Scanner scan;

    admin(User user) {
        if (user.getRole().trim().equalsIgnoreCase("admin")) {
            System.out.println("good day admin want to modify/update bus data");
        } else {
            System.out.println("Sry only admin can control the data ....");
        }
    }

}

public class TicketBooking {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 1. Setup the System and User
        nammaBusYatra system = new nammaBusYatra(scan);
        User currentUser = new User(scan);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n========= NAMMA YATRA MENU =========");
            System.out.println("1. View and Book a Bus");
            System.out.println("2. [ADMIN] Register a New Bus");
            System.out.println("3. Exit System");
            System.out.print("Please select an option: ");

            try {
                int choice = scan.nextInt();
                scan.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        system.startBookingFlow();
                        break;
                    case 2:
                        system.createBus(currentUser);
                        break;
                    case 3:
                        System.out.println("Thank you for using Namma Yatra. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a number.");
                scan.nextLine(); // Clear buffer
            }
        }
        scan.close();
    }
}
