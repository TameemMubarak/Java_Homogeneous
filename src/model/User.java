package model;

import java.util.Scanner;
import java.util.regex.Pattern;

public class User {

    private final String NAME;
    private final String PASSWORD;
    private final long NUMBER;
    private final String EMAIL;
    private final String ROLE;

    // REGEX PATTERNS

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[A-Za-z ]{3,40}$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile(
                    "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[6-9]\\d{9}$");

    // GETTERS

    public String getRole() {
        return ROLE;
    }

    public String getName() {
        return NAME;
    }

    public String getEmail() {
        return EMAIL;
    }

    public long getNumber() {
        return NUMBER;
    }

    // CONSTRUCTOR

    public User(Scanner scan) {

        String tempName;
        String tempRole;
        String tempPassword;
        String tempEmail;
        long tempNumber;

        System.out.println("\n========== USER REGISTRATION ==========");

        // NAME VALIDATION

        while (true) {
            try {

                System.out.print("Enter Full Name: ");
                tempName = scan.nextLine().trim();

                if (!NAME_PATTERN.matcher(tempName).matches()) {
                    throw new IllegalArgumentException(
                            "Name should contain only alphabets and spaces (3-40 characters).");
                }

                break;

            } catch (IllegalArgumentException e) {
                System.out.println("[INVALID NAME] " + e.getMessage());
            }
        }

        // ROLE VALIDATION

        while (true) {
            try {

                System.out.print("Enter Role (Admin/Passenger): ");
                tempRole = scan.nextLine().trim();

                if (!(tempRole.equalsIgnoreCase("Admin")
                        || tempRole.equalsIgnoreCase("Passenger"))) {

                    throw new IllegalArgumentException(
                            "Role must be either 'Admin' or 'Passenger'.");
                }

                break;

            } catch (IllegalArgumentException e) {
                System.out.println("[INVALID ROLE] " + e.getMessage());
            }
        }

        // EMAIL VALIDATION

        while (true) {
            try {

                System.out.print("Enter Email Address: ");
                tempEmail = scan.nextLine().trim();

                if (!EMAIL_PATTERN.matcher(tempEmail).matches()) {

                    throw new IllegalArgumentException(
                            "Invalid email format.");
                }

                break;

            } catch (IllegalArgumentException e) {
                System.out.println("[INVALID EMAIL] " + e.getMessage());
            }
        }

        // PASSWORD VALIDATION

        while (true) {
            try {

                System.out.println("""

Password Requirements:
- 8 to 20 characters
- At least one uppercase letter
- At least one lowercase letter
- At least one digit
- At least one special character
""");

                System.out.print("Create Password: ");

                tempPassword = scan.nextLine();

                if (!PASSWORD_PATTERN.matcher(tempPassword).matches()) {

                    throw new IllegalArgumentException(
                            "Weak password! Follow all password rules.");
                }

                break;

            } catch (IllegalArgumentException e) {
                System.out.println("[INVALID PASSWORD] " + e.getMessage());
            }
        }

        // MOBILE NUMBER VALIDATION

        while (true) {
            try {

                System.out.print("Enter Mobile Number: ");

                String phoneInput = scan.nextLine().trim();

                if (!PHONE_PATTERN.matcher(phoneInput).matches()) {

                    throw new IllegalArgumentException(
                            "Mobile number must contain exactly 10 digits and should start from 6-9.");
                }

                tempNumber = Long.parseLong(phoneInput);

                break;

            } catch (NumberFormatException e) {

                System.out.println(
                        "[INVALID NUMBER] Only numerical values are allowed.");

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "[INVALID PHONE] " + e.getMessage());
            }
        }

        // FINAL ASSIGNMENTS

        this.NAME = tempName;

        this.ROLE = tempRole.equalsIgnoreCase("admin")
                ? "ADMIN"
                : "PASSENGER";

        this.PASSWORD = tempPassword;

        this.EMAIL = tempEmail;

        this.NUMBER = tempNumber;

        // SUCCESS OUTPUT

        System.out.println("\n=======================================");
        System.out.println("[ACCOUNT CREATED SUCCESSFULLY]");
        System.out.println("Welcome, " + NAME + "!");
        System.out.println("Registered Role : " + ROLE);
        System.out.println("Registered Email: " + EMAIL);
        System.out.println("=======================================\n");

        System.out.println(
                "Now please login again using your credentials.");
    }
}