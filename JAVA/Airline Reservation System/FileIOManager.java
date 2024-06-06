import java.io.*;
import java.util.ArrayList;

public class FileIOManager {
    private static final String FILE_PATH = "reservations.txt";

    public static void writeReservationLinkedListToFile(ReservationLinkedList reservationList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(reservationList);
            System.out.println("Reservations written to file.");
        } catch (IOException e) {
            System.err.println("Error writing reservations to file: " + e.getMessage());
        }
    }

    public static ReservationLinkedList readReservationLinkedListFromFile() {
        ReservationLinkedList reservationList = new ReservationLinkedList();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            reservationList = (ReservationLinkedList) inputStream.readObject();
            System.out.println("Reservations read from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading reservations from file: " + e.getMessage());
        }
        return reservationList;
    }
}