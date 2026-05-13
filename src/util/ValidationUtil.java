package util;

import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean isValidBusName(String name) {
        return Pattern.matches(
                "^[A-Za-z]+(To|_to_)[A-Za-z]+$",
                name);
    }

    public static boolean isValidVehicleNumber(String number) {
        return Pattern.matches(
                "^[A-Z]{2}\\s\\d{2}\\s[A-Z]{1,2}\\s\\d{4}$",
                number);
    }
}