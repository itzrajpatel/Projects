import java.util.*;
import java.io.*;

public class Flight extends Airline {
    private int ticketPrice;
    private String flightDate;
    private String departureTime; // Add departure time as an attribute
    private String arrivalTime;   // Add arrival time as an attribute

    // Constructor
    public Flight(String flightNumber, String origin, String destination, String flightDate, String departureTime, String arrivalTime, int capacity, int ticketPrice) {
        super(flightNumber, origin, destination, capacity);
        this.ticketPrice = ticketPrice;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    // Getter for ticketPrice
    public double getTicketPrice() {
        return ticketPrice;
    }

    // Setter for ticketPrice
    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    // Setter method for departureTime
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    // Getter method for arrivalTime
    public String getArrivalTime() {
        return arrivalTime;
    }

    // Setter method for arrivalTime
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    // Getter method for flightDate
    public String getFlightDate() {
        return flightDate;
    }

    // Setter method for flightDate
    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }
}