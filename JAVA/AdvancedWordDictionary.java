import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class AdvancedWordDictionary {
    public static void main(String[] args) {
        Map<String, String> dictionary = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Search for a word");
            System.out.println("2. Add a new word");
            System.out.println("3. Update a definition");
            System.out.println("4. Remove a word");
            System.out.println("5. Exit");
            System.out.print("Enter option number: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (option) {
                case 1:
                    System.out.println("Enter a word to search for: ");
                    String searchWord = scanner.nextLine();
                    String definition = dictionary.get(searchWord);
                    if (definition != null) {
                        System.out.println("Definition: " + definition);
                    } else {
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the new word: ");
                    String newWord = scanner.nextLine();
                    if (!dictionary.containsKey(newWord)) {
                        System.out.print("Enter the definition: ");
                        String newDefinition = scanner.nextLine();
                        dictionary.put(newWord, newDefinition);
                        System.out.println("Word added to the dictionary.");
                    } else {
                        System.out.println("Word already exists in the dictionary.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the word to update: ");
                    String updateWord = scanner.nextLine();
                    if (dictionary.containsKey(updateWord)) {
                        System.out.print("Enter the new definition: ");
                        String updatedDefinition = scanner.nextLine();
                        dictionary.put(updateWord, updatedDefinition);
                        System.out.println("Definition updated.");
                    } else {
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the word to remove: ");
                    String removeWord = scanner.nextLine();
                    if (dictionary.containsKey(removeWord)) {
                        dictionary.remove(removeWord);
                        System.out.println("Word removed from the dictionary.");
                    } else {
                        System.out.println("Word not found in the dictionary.");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}