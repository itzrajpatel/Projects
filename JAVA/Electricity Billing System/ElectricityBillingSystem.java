import java.io.*;
import java.util.Scanner;

class ElectricityBill {
    private String customerName;
    private int unitsConsumed;
    private double totalBill;

    public ElectricityBill(String customerName, int unitsConsumed) {
        this.customerName = customerName;
        this.unitsConsumed = unitsConsumed;
        this.totalBill = calculateBill();
    }

    private double calculateBill() {
        // Calculate the electricity bill based on your pricing logic
        // You can modify this calculation as per your requirements
        double ratePerUnit = 5.0; // Adjust the rate as needed
        return unitsConsumed * ratePerUnit;
    }

    public void printBill() {
        System.out.println("Customer Name: " + customerName);
        System.out.println("Units Consumed: " + unitsConsumed);
        System.out.println("Total Bill: ₹" + totalBill);
    }

    public String toFileString() {
        // Convert the data to a string format for writing to a file
        return customerName + "," + unitsConsumed + "," + totalBill;
    }
}

public class ElectricityBillingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Customer Name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter Units Consumed:");
        int unitsConsumed = scanner.nextInt();

        try {
            // Create an ElectricityBill object
            ElectricityBill bill = new ElectricityBill(customerName, unitsConsumed);

            // Print the bill details
            bill.printBill();

            // Write the bill details to a file
            writeBillToFile(bill);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void writeBillToFile(ElectricityBill bill) {
        try (FileWriter writer = new FileWriter("electricity_bills.txt", true)) {
            // Append the bill details to the file
            writer.write(bill.toFileString() + "\n");
            System.out.println("Bill written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}