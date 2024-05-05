import java.util.*;
import java.io.*;

// Car class represents a car object with name and daily rental rate
class Car {
    private String name;
    private double dailyRate;

    // Constructor to initialize car with name and daily rate
    public Car(String name, double dailyRate) {
        this.name = name;
        this.dailyRate = dailyRate;
    }

    // Getter method for car name
    public String getName() {
        return name;
    }

    // Getter method for daily rental rate
    public double getDailyRate() {
        return dailyRate;
    }
}

// User class represents a user object with username and password
class User {
    private String username;
    private String password;

    // Constructor to initialize user with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }
}

// RentalRecord class represents a rental record with user, car, rental days, and total amount
class RentalRecord {
    private User user;
    private Car car;
    private int rentalDays;
    private double totalAmount;

    // Constructor to initialize rental record with user, car, and rental days
    public RentalRecord(User user, Car car, int rentalDays) {
        this.user = user;
        this.car = car;
        this.rentalDays = rentalDays;
        this.totalAmount = rentalDays * car.getDailyRate();
    }

    // Getter method for user
    public User getUser() {
        return user;
    }

    // Getter method for car
    public Car getCar() {
        return car;
    }

    // Getter method for rental days
    public int getRentalDays() {
        return rentalDays;
    }

    // Getter method for total amount
    public double getTotalAmount() {
        return totalAmount;
    }
}

// Main class that implements the car rental system
public class CarRentalSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static List<RentalRecord> rentalRecords = new ArrayList<>();
    private static User loggedInUser = null;

    // Main method to start the program
    public static void main(String[] args) {
        initializeData();

        // Main menu loop
        while (true) {
            System.out.println("===============================================================================");
            System.out.println("                        Welcome to the Car Rental System                       ");
            System.out.println("===============================================================================");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 2:
                    login();
                    break;
                case 1:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Initialize some sample data
    private static void initializeData() {
        cars.add(new Car("Toyota Fortuner", 700.0));
        // Add more cars if needed
        users.add(new User("user1", "password1"));
        // Add more users if needed
    }

    // Method to handle user login
    private static void login() {
        System.out.println("");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        loggedInUser = authenticateUser(username, password);

        if (loggedInUser != null) {
            System.out.println("Login successful!");
            mainMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    // Method to authenticate user
    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Method to handle user registration
    private static void register() {
        System.out.print("Enter a new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter a new password: ");
        String newPassword = scanner.nextLine();

        users.add(new User(newUsername, newPassword));
        System.out.println("Registration successful. You can now log in.");
    }

    // Main menu method after user login
    private static void mainMenu() {
        while (true) {
            System.out.println("=========");
            System.out.println("Main Menu");
            System.out.println("=========");
            System.out.println("1. Rent a Car");
            System.out.println("2. View Rental History");
            System.out.println("3. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    rentCar();
                    break;
                case 2:
                    viewRentalHistory();
                    break;
                case 3:
                    loggedInUser = null;
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle renting a car
    private static void rentCar() {
        if (loggedInUser != null) {
            System.out.println();
            System.out.println("Available Cars:");
            System.out.println("=====================================");
            System.out.println(String.format("%-5s %-20s %-10s", "No.", "Car Name", "Daily Rate"));
            System.out.println("=====================================");

            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                String carInfo = String.format("%-5s %-20s $%-10.2f", (i + 1), car.getName(), car.getDailyRate());
                System.out.println(carInfo);
            }
            System.out.println("=====================================");

            System.out.print("Select a car (enter the number): ");
            int carChoice = scanner.nextInt();
            if (carChoice >= 1 && carChoice <= cars.size()) {
                Car selectedCar = cars.get(carChoice - 1);

                System.out.print("Enter rental days: ");
                int rentalDays = scanner.nextInt();

                double totalAmount = selectedCar.getDailyRate() * rentalDays;

                RentalRecord rentalRecord = new RentalRecord(loggedInUser, selectedCar, rentalDays);
                rentalRecords.add(rentalRecord);

                System.out.println("Rental successful!");
                System.out.println("Total amount: $" + totalAmount + "/-");
            } else {
                System.out.println("Invalid car selection.");
            }
        } else {
            System.out.println("You must log in to rent a car.");
        }
    }

    // Method to view rental history for the logged-in user
    private static void viewRentalHistory() {
        if (loggedInUser != null) {
            System.out.println("=====================================");
            System.out.println("Rental History for " + loggedInUser.getUsername() + ":");
            System.out.println("=====================================");
            for (RentalRecord record : rentalRecords) {
                if (record.getUser().equals(loggedInUser)) {
                    System.out.println("Car: " + record.getCar().getName());
                    System.out.println("Rental Days: " + record.getRentalDays());
                    System.out.println("Total Amount: $" + record.getTotalAmount());
                    System.out.println("------------------------");
                }
            }
        } else {
            System.out.println("You must log in to view rental history.");
        }
    }
}
