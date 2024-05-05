import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdvancedWordDictionary {
    public static void main(String[] args) {
        // Create a HashMap to store words and their definitions
        Map<String, String> dictionary = new HashMap<>();
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Display options to the user in a loop
        while (true) {
            System.out.println("Options:");
            System.out.println("1. Search for a word");
            System.out.println("2. Add a new word");
            System.out.println("3. Update a definition");
            System.out.println("4. Remove a word");
            System.out.println("5. Exit");
            System.out.print("Enter option number: ");

            // Read the user's choice
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            // Perform different actions based on the user's choice
            switch (option) {
                case 1:
                    // Search for a word
                    System.out.println("Enter a word to search for: ");
                    String searchWord = scanner.nextLine();
                    String definition = dictionary.get(searchWord);
                    if (definition != null) {
                        // Display the definition if the word is found
                        System.out.println("Definition: " + definition);
                    } else {
                        // Display a message if the word is not found
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 2:
                    // Add a new word and its definition
                    System.out.println("Enter the new word: ");
                    String newWord = scanner.nextLine();
                    if (!dictionary.containsKey(newWord)) {
                        // Check if the word already exists in the dictionary
                        System.out.print("Enter the definition: ");
                        String newDefinition = scanner.nextLine();
                        // Add the word and its definition to the dictionary
                        dictionary.put(newWord, newDefinition);
                        System.out.println("Word added to the dictionary.");
                    } else {
                        // Display a message if the word already exists
                        System.out.println("Word already exists in the dictionary.");
                    }
                    break;
                case 3:
                    // Update the definition of an existing word
                    System.out.println("Enter the word to update: ");
                    String updateWord = scanner.nextLine();
                    if (dictionary.containsKey(updateWord)) {
                        // Check if the word exists in the dictionary
                        System.out.print("Enter the new definition: ");
                        String updatedDefinition = scanner.nextLine();
                        // Update the definition of the word
                        dictionary.put(updateWord, updatedDefinition);
                        System.out.println("Definition updated.");
                    } else {
                        // Display a message if the word is not found
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 4:
                    // Remove a word from the dictionary
                    System.out.println("Enter the word to remove: ");
                    String removeWord = scanner.nextLine();
                    if (dictionary.containsKey(removeWord)) {
                        // Check if the word exists in the dictionary
                        dictionary.remove(removeWord);
                        System.out.println("Word removed from the dictionary.");
                    } else {
                        // Display a message if the word is not found
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 5:
                    // Exit the program
                    System.out.println("Goodbye!");
                    scanner.close(); // Close the scanner
                    System.exit(0);
                default:
                    // Display a message for an invalid option
                    System.out.println("Invalid option");
            }
        }
    }
}