import java.util.*;
import java.io.*;

public class Reservation {
    private Flight flight;
    private Passenger passenger;

    // Constructor
    public Reservation(Flight flight, Passenger passenger) {
        //this.reservationId = reservationId;
        this.flight = flight;
        this.passenger = passenger;
    }

    // Getter for flight
    public Flight getFlight() {
        return flight;
    }

    // Setter for flight
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    // Getter for passenger
    public Passenger getPassenger() {
        return passenger;
    }

    // Setter for passenger
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
