package service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import model.User;
import util.ValidationUtil;

public class AuthService {

    // IN-MEMORY USER DATABASE

    private final Map<String, User> users =
            new HashMap<>();

    // CURRENT SESSION USER

    private User currentLoggedInUser;

    // REGISTER USER

    public void register(Scanner scan) {

        System.out.println(
                "\n========== USER REGISTRATION ==========");

        String name;
        String email;
        String password;
        long mobileNumber;
        String role;

        // NAME VALIDATION

        while (true) {

            System.out.print(
                    "Enter Full Name: ");

            name =
                    scan.nextLine().trim();

            if (!ValidationUtil.isValidName(name)) {

                System.out.println(
                        "[VALIDATION ERROR] Name must contain only alphabets and spaces (3-50 characters).");

                continue;
            }

            break;
        }

        // EMAIL VALIDATION

        while (true) {

            System.out.print(
                    "Enter Email Address: ");

            email =
                    scan.nextLine().trim();

            if (!ValidationUtil.isValidEmail(email)) {

                System.out.println(
                        "[VALIDATION ERROR] Invalid email address format.");

                continue;
            }

            if (users.containsKey(email)) {

                System.out.println(
                        "[AUTH ERROR] Email already registered.");

                continue;
            }

            break;
        }

        // PASSWORD VALIDATION

        while (true) {

            System.out.print(
                    "Create Password: ");

            password =
                    scan.nextLine().trim();

            if (!ValidationUtil.isValidPassword(password)) {

                System.out.println(
                        "[VALIDATION ERROR] Password must contain:");
                System.out.println(
                        "- Minimum 8 characters");
                System.out.println(
                        "- One uppercase letter");
                System.out.println(
                        "- One lowercase letter");
                System.out.println(
                        "- One digit");
                System.out.println(
                        "- One special character");

                continue;
            }

            break;
        }

        // MOBILE NUMBER VALIDATION

        while (true) {

            try {

                System.out.print(
                        "Enter Mobile Number: ");

                String mobileInput =
                        scan.nextLine().trim();

                if (!ValidationUtil.isValidMobileNumber(
                        mobileInput)) {

                    throw new IllegalArgumentException(
                            "Mobile number must contain exactly 10 digits and start from 6-9.");
                }

                mobileNumber =
                        Long.parseLong(mobileInput);

                break;

            } catch (NumberFormatException e) {

                System.out.println(
                        "[INPUT ERROR] Mobile number must contain digits only.");

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "[VALIDATION ERROR] "
                                + e.getMessage());
            }
        }

        // ROLE VALIDATION

        while (true) {

            System.out.print(
                    "Enter Role (ADMIN/PASSENGER): ");

            role =
                    scan.nextLine()
                            .trim()
                            .toUpperCase();

            if (!(role.equals("ADMIN")
                    || role.equals("PASSENGER"))) {

                System.out.println(
                        "[VALIDATION ERROR] Role must be ADMIN or PASSENGER.");

                continue;
            }

            break;
        }

        // USER ID GENERATION

        String userId =
                "USR-"
                        + UUID.randomUUID()
                                .toString()
                                .substring(0, 5)
                                .toUpperCase();

        // CREATE USER OBJECT

        User newUser =
                new User(
                        userId,
                        name,
                        email,
                        password,
                        mobileNumber,
                        role);

        // STORE USER

        users.put(email, newUser);

        // SUCCESS MESSAGE

        System.out.println(
                "\n[SUCCESS] Account created successfully.");

        System.out.println(
                "User ID        : " + userId);

        System.out.println(
                "Assigned Role  : " + role);

        System.out.println(
                "======================================");
    }

    // LOGIN USER

    public User login(Scanner scan) {

        System.out.println(
                "\n========== USER LOGIN ==========");

        System.out.print(
                "Enter Email Address: ");

        String email =
                scan.nextLine().trim();

        System.out.print(
                "Enter Password: ");

        String password =
                scan.nextLine().trim();

        // EMAIL CHECK

        if (!users.containsKey(email)) {

            System.out.println(
                    "[AUTH ERROR] No account found with this email.");

            return null;
        }

        User foundUser =
                users.get(email);

        // PASSWORD CHECK

        if (!foundUser.getPassword()
                .equals(password)) {

            System.out.println(
                    "[AUTH ERROR] Incorrect password.");

            return null;
        }

        // SESSION CREATION

        currentLoggedInUser =
                foundUser;

        System.out.println(
                "\n[SUCCESS] Login successful.");

        System.out.println(
                "Welcome Back   : "
                        + foundUser.getName());

        System.out.println(
                "Session Role   : "
                        + foundUser.getRole());

        System.out.println(
                "User ID        : "
                        + foundUser.getUserId());

        System.out.println(
                "================================");

        return foundUser;
    }

    // LOGOUT

    public void logout() {

        if (currentLoggedInUser == null) {

            System.out.println(
                    "[AUTH ERROR] No active session found.");

            return;
        }

        System.out.println(
                "\n[SUCCESS] Logout successful.");

        System.out.println(
                "Goodbye, "
                        + currentLoggedInUser.getName()
                        + ".");

        currentLoggedInUser = null;
    }

    // SESSION GETTER

    public User getCurrentLoggedInUser() {
        return currentLoggedInUser;
    }

    // USER DATABASE GETTER

    public Map<String, User> getUsers() {
        return users;
    }
}