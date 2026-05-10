import java.util.*;
import java.util.regex.Pattern;

class Bus {

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

    Bus(Scanner scan) {
        this.scan = scan;
        System.out.println("\n===== Initializing New Bus Registration =====");

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
    boolean bookSeat() {
        if (occupiedSeats == seats) {
            System.out.println("No seats are left ....");
            return false;
        } else {
            boolean paid = payment();
            if (!paid) {
                System.out.println("Pls complete the payment to book your seat...");
                return false;
            }
        }
        occupiedSeats++;
        vacantSeats--;
        System.out.println("seat has been reserved sucessfully...-_-:)");
        return true;
    }

    boolean payment() {
        System.out.println("Kindly pay the amount to confirm your seat");
        try {
            int amountPaid = scan.nextInt();
            if (amountPaid == price)
                return true;
            else if (amountPaid < price) {
                System.out.println("Pls pay total  amount ..." + price);
            } else {
                System.out.println(
                        "The remaining amount" + (amountPaid - price) + " will be refunded within 3 working days.");
                return true;
            }
        } catch (InputMismatchException e) {
            System.out.println("please pay an amount of " + price + " rupees to Confirm your reservation.");
        }

        return false;

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
            scan.nextLine(); 
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

    void createBus(User user) {
        if (!user.getRole().trim().equalsIgnoreCase("admin")) {
            System.out.println("Acess denied..!");
            System.out.println("Only admin have acess");
            return;
        }
        String busName = scan.nextLine();
        int busNumber = scan.nextInt();
        int seatsAvailable = scan.nextInt();

    }
}

public class TicketBooking {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

    }
}

