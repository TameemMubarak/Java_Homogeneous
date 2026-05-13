package model;

public class Bus {

    // INSTANCE VARIABLES

    private final String busId;

    private String busName;
    private String vehicleNumber;

    private int engineCapacity;
    private int ticketPrice;

    private int totalSeats;
    private int occupiedSeats;
    private int vacantSeats;

    // CONSTRUCTOR

    public Bus(
            String busId,
            String busName,
            String vehicleNumber,
            int engineCapacity,
            int ticketPrice,
            int totalSeats) {

        this.busId = busId;

        this.busName = busName;

        this.vehicleNumber = vehicleNumber;

        this.engineCapacity = engineCapacity;

        this.ticketPrice = ticketPrice;

        this.totalSeats = totalSeats;

        this.occupiedSeats = 0;

        this.vacantSeats = totalSeats;
    }

    // DISPLAY METHODS

    public void displayAvailableSeats() {

        System.out.println(
                "Available Seats : "
                        + vacantSeats);
    }

    public void displayReservedSeats() {

        System.out.println(
                "Reserved Seats : "
                        + occupiedSeats);
    }

    // GETTERS

    public String getBusId() {
        return busId;
    }

    public String getBusName() {
        return busName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public int getVacantSeats() {
        return vacantSeats;
    }

    // SETTERS

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public void setVacantSeats(int vacantSeats) {
        this.vacantSeats = vacantSeats;
    }

    // TOSTRING

    @Override
    public String toString() {

        return "Bus{"
                + "busId='"
                + busId
                + '\''
                + ", busName='"
                + busName
                + '\''
                + ", vehicleNumber='"
                + vehicleNumber
                + '\''
                + ", engineCapacity="
                + engineCapacity
                + ", ticketPrice="
                + ticketPrice
                + ", totalSeats="
                + totalSeats
                + ", occupiedSeats="
                + occupiedSeats
                + ", vacantSeats="
                + vacantSeats
                + '}';
    }
}