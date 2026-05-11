package model;

import java.util.Scanner;

public class User {
    private final String NAME;
    private final String pwd;
    private final long NUMBER;
    private final String EMAIL;
    private final String ROLE;

    public String getRole() {
        return this.ROLE;
    }

    public User(Scanner scan) {
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
