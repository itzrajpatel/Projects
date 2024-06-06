import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class ReservationLinkedList implements Serializable {
    private LinkedList<Reservation> reservations;
    private static final String FILE_PATH = "reservations.txt"; // File for storing reservations

    public ReservationLinkedList() {
        reservations = new LinkedList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void displayReservations() {
        for (Reservation res : reservations) {
            System.out.println("Flight: " + res.getFlight().getFlightNumber());
            System.out.println("Passenger: " + res.getPassenger().getName());
            System.out.println("Origin: " + res.getFlight().getOrigin());
            System.out.println("Destination: " + res.getFlight().getDestination());
            System.out.println("Ticket Price: " + res.getFlight().getTicketPrice());
            System.out.println("------------------------------");
        }
    }

    private void saveReservationsToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(reservations);
        } catch (IOException e) {
            System.out.println("Error saving reservations to file: " + e.getMessage());
        }
    }

    private void loadReservationsFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            reservations = (LinkedList<Reservation>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading reservations from file: " + e.getMessage());
        }
    }
}
