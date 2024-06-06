import java.sql.*;
import java.util.*;
import java.io.*;

public class DatabaseManager {
    private static final String url = "jdbc:mysql://localhost:3306/airline_db";
    private static final String user = "root";
    private static final String pass = "";

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    // Method to retrieve data from the database (example: get all flights)
    public static List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM flights";
            try (PreparedStatement st = con.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String flightNumber = rs.getString("flight_number");
                    String origin = rs.getString("origin");
                    String destination = rs.getString("destination");
                    String flightDate = rs.getString("flight_date");
                    String departureTime = rs.getString("departure_time");
                    String arrivalTime = rs.getString("arrival_time");
                    int capacity = rs.getInt("capacity");
                    int ticketPrice = rs.getInt("ticket_price");
                    Flight flight = new Flight(flightNumber, origin, destination, flightDate, departureTime, arrivalTime, capacity, ticketPrice);
                    flights.add(flight);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    // Add this method to DatabaseManager to retrieve all passengers
public static List<Passenger> getAllPassengers() {
    List<Passenger> passengers = new ArrayList<>();
    try (Connection connection = getConnection()) {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM passengers");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String age = resultSet.getString("age");
            String name = resultSet.getString("name");
            String passportNumber = resultSet.getString("passport_number");
            Passenger passenger = new Passenger(age, name, passportNumber);
            passengers.add(passenger);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return passengers;
}


    // Method to insert data into the database (example: save a new flight)
    public static void saveFlight(Flight flight) {
        try (Connection con = getConnection()) {
            // Disable auto-commit to manage the transaction manually
            con.setAutoCommit(false);
            String sql = "INSERT INTO flights (flight_number, origin, destination, flight_date, departure_time, arrival_time, capacity, ticket_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement st = con.prepareStatement(sql)){
                st.setString(1, flight.getFlightNumber());
                st.setString(2, flight.getOrigin());
                st.setString(3, flight.getDestination());
                st.setString(4, flight.getFlightDate());
                st.setString(5, flight.getDepartureTime());
                st.setString(6, flight.getArrivalTime());
                st.setInt(7, flight.getCapacity());
                st.setDouble(8, flight.getTicketPrice());
                int rowsInserted = st.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Flight record inserted successfully.");
                } else {
                    System.out.println("Flight record insertion failed.");
                }
                con.commit();
            } catch (SQLException e) {
                // If there's an error, roll back the transaction to avoid partial updates
                con.rollback();
                e.printStackTrace(); // Print or handle the error
            } finally {
                // Re-enable auto-commit when you're done
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void passenger(Passenger passenger) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO passengers (age, name, passport_number) VALUES (?, ?, ?)"
            );
            statement.setString(1, passenger.getAge());
            statement.setString(2, passenger.getName());
            statement.setString(3, passenger.getPassportNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateFlight(String flightNumber, Flight updateFlight) {
        try (Connection connection = getConnection()) {
            String sql = "UPDATE flights SET origin = ?, destination = ?, flight_date = ?, departure_time = ?, arrival_time = ?, capacity = ?, ticket_price = ? WHERE flight_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, updateFlight.getOrigin());
            statement.setString(2, updateFlight.getDestination());
            statement.setString(3, updateFlight.getFlightDate());
            statement.setString(4, updateFlight.getDepartureTime());
            statement.setString(5, updateFlight.getArrivalTime());
            statement.setInt(6, updateFlight.getCapacity());
            statement.setDouble(7, updateFlight.getTicketPrice());
            statement.setString(8, flightNumber); // Use the flightNumber parameter here
        
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Flight details updated successfully.");
            } else {
                System.out.println("Flight details update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    

    public static Flight getFlightByFlightNumber(String flightNumber) {
        Flight flight = null;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM flights WHERE flight_number = ?");
            statement.setString(1, flightNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String origin = resultSet.getString("origin");
                String destination = resultSet.getString("destination");
                String departureTime = resultSet.getString("departure_time");
                String arrivalTime = resultSet.getString("arrival_time");
                String flightDate = resultSet.getString("flight_date");
                int capacity = resultSet.getInt("capacity");
                int ticketPrice = resultSet.getInt("ticket_price");

                flight = new Flight(flightNumber, origin, destination, flightDate, departureTime, arrivalTime, capacity, ticketPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public static boolean flightExists(String flightNumber) {
        boolean exists = false;
        try (Connection connection = getConnection()) {
            String sql = "SELECT COUNT(*) FROM flights WHERE flight_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, flightNumber);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = (count > 0); // If count > 0, the flight exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static boolean passengerExists(String name, String passportNumber) {
        boolean exists = false;
        try (Connection connection = getConnection()) {
            String sql = "SELECT COUNT(*) FROM passengers WHERE name = ? AND passport_number = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, passportNumber);
    
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                exists = (count > 0); // If count > 0, the passenger exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }    
}