package service;

import java.util.Scanner;

public class PaymentService {

    public boolean processPayment(
            int amount,
            Scanner scan) {

        System.out.println(
                "Amount To Pay: " + amount);

        try {

            int paid =
                    Integer.parseInt(
                            scan.nextLine());

            if (paid < amount) {
                System.out.println(
                        "Insufficient payment.");
                return false;
            }

            System.out.println(
                    "Payment successful.");

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Invalid payment.");

            return false;
        }
    }
}