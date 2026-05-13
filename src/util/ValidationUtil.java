package util;

import java.util.regex.Pattern;

public class ValidationUtil {

    // BUS NAME VALIDATION

    public static boolean isValidBusName(String name) {

        return Pattern.matches(
                "^[A-Za-z]+(To|_to_)[A-Za-z]+$",
                name);
    }

    // VEHICLE NUMBER VALIDATION

    public static boolean isValidVehicleNumber(String number) {

        return Pattern.matches(
                "^[A-Z]{2}\\s\\d{2}\\s[A-Z]{1,2}\\s\\d{4}$",
                number);
    }

    // NAME VALIDATION

    public static boolean isValidName(String name) {

        return Pattern.matches(
                "^[A-Za-z ]{3,50}$",
                name);
    }

    // EMAIL VALIDATION

    public static boolean isValidEmail(String email) {

        return Pattern.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                email);
    }

    // PASSWORD VALIDATION

    public static boolean isValidPassword(String password) {

        return Pattern.matches(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
                password);
    }

    // MOBILE NUMBER VALIDATION

    public static boolean isValidMobileNumber(String mobile) {

        return Pattern.matches(
                "^[6-9]\\d{9}$",
                mobile);
    }
}