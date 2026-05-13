package service;

import java.util.Scanner;

import model.Bus;

public class BusService {

    private final PaymentService paymentService =
            new PaymentService();

    public boolean bookSeat(
            Bus bus,
            int requestedSeats,
            Scanner scan) {

        if (requestedSeats <= 0) {

            System.out.println(
                    "[ERROR] Invalid seat count.");

            return false;
        }

        if (requestedSeats > bus.getVacantSeats()) {

            System.out.println(
                    "[ERROR] Only "
                            + bus.getVacantSeats()
                            + " seats available.");

            return false;
        }

        int totalAmount =
                requestedSeats
                        * bus.getTicketPrice();

        boolean paymentStatus =
                paymentService.processPayment(
                        totalAmount,
                        scan);

        if (!paymentStatus) {

            return false;
        }

        bus.setOccupiedSeats(
                bus.getOccupiedSeats()
                        + requestedSeats);

        bus.setVacantSeats(
                bus.getVacantSeats()
                        - requestedSeats);

        return true;
    }
}