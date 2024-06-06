import java.util.*;

public class AirlineReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Reservation> reservations = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        String flightNumber = "";
        String origin = "";
        String destination = "";
        String departureTime = "";
        String arrivalTime = "";
        String flightDate = "";
        int capacity = 0;
        int ticketPrice = 0;

        Reservation reservation; // Declare reservation variable here
        Passenger passenger;

        while (true) {
            // Display menu options
            System.out.println("=================================");
            System.out.println("   Airline Reservation System    ");
            System.out.println("=================================");
            System.out.println("1. Insert Flight Details");
            System.out.println("2. Update Flight Details");
            System.out.println("3. Show Flight Details");
            System.out.println("4. Show Passenger Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Insert Flight Details
                    System.out.println("Enter details for Flight:");
                    System.out.print("Flight Number: ");
                    flightNumber = scanner.nextLine();
                    if (DatabaseManager.flightExists(flightNumber)) {
                        System.out.println("Flight with the same number already exists.");
                    } else {
                    System.out.print("Origin: ");
                    origin = scanner.nextLine();
                    System.out.print("Destination: ");
                    destination = scanner.nextLine();
                    System.out.print("Flight Date: ");
                    flightDate = scanner.nextLine();
                    System.out.print("Departure Time: ");
                    departureTime = scanner.nextLine();
                    System.out.print("Arrival Time: ");
                    arrivalTime = scanner.nextLine();
                    System.out.print("Capacity(in kg): ");
                    capacity = scanner.nextInt();
                    System.out.print("Ticket Price: ");
                    ticketPrice = scanner.nextInt();
                    scanner.nextLine();

                    Flight flight = new Flight(flightNumber, origin, destination, flightDate, departureTime, arrivalTime, capacity, ticketPrice);
                    DatabaseManager.saveFlight(flight);
                    if (!DatabaseManager.flightExists(flightNumber)) {
                        DatabaseManager.saveFlight(flight);
                        System.out.println("Flight record inserted successfully.");
                    }

                    System.out.println("Enter details for Passenger:");
                    System.out.print("Age: ");
                    String age = scanner.nextLine();
                    System.out.print("Name: ");
                    String passengerName = scanner.nextLine();
                    System.out.print("Passport Number: ");
                    String passportNumber = scanner.nextLine();

                    passenger = new Passenger(age, passengerName, passportNumber);
                    DatabaseManager.passenger(passenger);

                    reservation = new Reservation(flight, passenger);
                    reservations.add(reservation);
                }
                    break;
                case 2:
                System.out.print("Enter Flight Number to update: ");
                String flightToUpdate = scanner.nextLine();
                
                Flight updateFlight = new Flight(flightNumber, origin, destination, flightDate, departureTime, arrivalTime, capacity, ticketPrice);
            
                // Check if the flight exists in the database
                if (DatabaseManager.flightExists(flightToUpdate)) {
                    System.out.println("Enter details to update for Flight " + flightToUpdate + ":");
                    System.out.print("Origin: ");
                    updateFlight.setOrigin(scanner.nextLine());
                    System.out.print("Destination: ");
                    updateFlight.setDestination(scanner.nextLine());
                    System.out.print("Flight Date: ");
                    updateFlight.setFlightDate(scanner.nextLine());
                    System.out.print("Departure Time: ");
                    updateFlight.setDepartureTime(scanner.nextLine());
                    System.out.print("Arrival Time: ");
                    updateFlight.setArrivalTime(scanner.nextLine());
                    System.out.print("Capacity(in kg): ");
                    updateFlight.setCapacity(scanner.nextInt());
                    System.out.print("Ticket Price: ");
                    updateFlight.setTicketPrice(scanner.nextInt());
                    scanner.nextLine();
            
                    // Call the updateFlight method to update flight details
                    DatabaseManager.updateFlight(flightToUpdate, updateFlight);
                } else {
                    System.out.println("Flight with number " + flightToUpdate + " does not exist.");
                }
                break;
                case 3:
                    // Show Flight Details
                    // Retrieve and display flight details using the getAllFlights method from DatabaseManager
                    List<Flight> flights = DatabaseManager.getAllFlights();

                    System.out.println("=================================");
                    System.out.println("         List of Flights         ");
                    System.out.println("=================================");
                    for (Flight flightDetail : flights) {
                        System.out.println("Flight Number: " + flightDetail.getFlightNumber());
                        System.out.println("Origin: " + flightDetail.getOrigin());
                        System.out.println("Destination: " + flightDetail.getDestination());
                        System.out.println("Capacity: " + flightDetail.getCapacity() + " Kg");
                        System.out.println("Flight Date: " + flightDetail.getFlightDate());
                        System.out.println("Departure Time: " + flightDetail.getDepartureTime());
                        System.out.println("Arrival Time: " + flightDetail.getArrivalTime());
                        System.out.println("Ticket Price: ₹" + flightDetail.getTicketPrice());
                        System.out.println();
                    }
                    break;
                    case 4:
                    // Show Passenger Details
                    List<Passenger> passengers = DatabaseManager.getAllPassengers();

                    System.out.println("=================================");
                    System.out.println("        List of Passengers       ");
                    System.out.println("=================================");
                    for (Passenger pass : passengers) {
                        System.out.println("Passenger Name: " + pass.getName());
                        System.out.println("Passenger Age: " + pass.getAge());
                        System.out.println("Passport Number: " + pass.getPassportNumber());
                        System.out.println();
                    }
                    break;
                case 5:
                    // Exit the program
                    System.out.println("Thank You!!!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}