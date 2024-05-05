import java.util.*;
import java.io.*;

class Car {
    private String name;
    private double dailyRate;

    public Car(String name, double dailyRate) {
        this.name = name;
        this.dailyRate = dailyRate;
    }

    public String getName() {
        return name;
    }

    public double getDailyRate() {
        return dailyRate;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class RentalRecord {
    private User user;
    private Car car;
    private int rentalDays;
    private double totalAmount;

    public RentalRecord(User user, Car car, int rentalDays) {
        this.user = user;
        this.car = car;
        this.rentalDays = rentalDays;
        this.totalAmount = rentalDays * car.getDailyRate();
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

public class CarRentalSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>();
    private static List<Car> cars = new ArrayList<>();
    private static List<RentalRecord> rentalRecords = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        initializeData();

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

    private static void initializeData() {
        // Initialize some cars and users (you can add more)
        cars.add(new Car("Toyota Fortuner", 700.0));
        cars.add(new Car("Toyota Innova", 650.0));
        cars.add(new Car("BMW X1",600.0));
        cars.add(new Car("BMW X3", 220.0));
        cars.add(new Car("Audi Q7", 1300.0));
        cars.add(new Car("Audi Q5", 900.0));
        cars.add(new Car("Mahindra XUV500", 600.0));
        cars.add(new Car("Mahindra XUV300", 450.0));
        cars.add(new Car("Tata Harrier", 550.0));
        cars.add(new Car("Tata Safari", 650.0));

        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
    }

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

    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void register() {
        System.out.print("Enter a new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter a new password: ");
        String newPassword = scanner.nextLine();

        users.add(new User(newUsername, newPassword));
        System.out.println("Registration successful. You can now log in.");
    }

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
            // Retrieve the selected car using cars.get(carChoice - 1)
            Car selectedCar = cars.get(carChoice - 1);
            
            System.out.print("Enter rental days: ");
            int rentalDays = scanner.nextInt();

            // Calculate the total amount based on the selected car and rental days
            double totalAmount = selectedCar.getDailyRate() * rentalDays;

            // Create a rental record and store it in the rentalRecords list
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
