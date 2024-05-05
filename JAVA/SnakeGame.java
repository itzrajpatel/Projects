import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Class representing the Snake Game
public class SnakeGame {
    public static void main(String[] args) {
        new SnakeGame().start(); // Start the game
    }

    // Define the game grid dimensions
    private final int width = 20;
    private final int height = 10;

    // Define variables for the snake, food, game over status, current direction, and random number generator
    private ArrayList<Position> snake;
    private Position food;
    private boolean isGameOver = false;
    private Direction currentDirection = Direction.RIGHT;
    private Random random = new Random();

    // Method to start the game
    public void start() {
        snake = new ArrayList<>(); // Initialize the snake
        snake.add(new Position(0, 0)); // Add the snake's initial position
        placeFood(); // Place food on the grid

        Scanner scanner = new Scanner(System.in); // Scanner for user input
        while (!isGameOver) { // Main game loop
            display(); // Display the game grid
            char input = scanner.next().charAt(0); // Read user input
            updateDirection(input); // Update snake direction based on input
            moveSnake(); // Move the snake
            checkCollision(); // Check for collisions
        }

        System.out.println("Game Over!"); // Display "Game Over" message
        scanner.close(); // Close the scanner
    }

    // Method to display the game grid
    private void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position pos = new Position(x, y); // Create a position object
                if (snake.contains(pos)) { // Check if position contains snake
                    if (snake.get(0).equals(pos)) {
                        System.out.print("O"); // Print snake's head
                    } else {
                        System.out.print("■"); // Print snake's body
                    }
                } else if (pos.equals(food)) { // Check if position contains food
                    System.out.print("F"); // Print food
                } else {
                    System.out.print("."); // Print empty space
                }
            }
            System.out.println(); // Move to the next line
        }
    }

    // Method to update snake direction based on user input
    private void updateDirection(char input) {
        switch (input) {
            case 'u': // Up
                currentDirection = Direction.UP;
                break;
            case 'd': // Down
                currentDirection = Direction.DOWN;
                break;
            case 'l': // Left
                currentDirection = Direction.LEFT;
                break;
            case 'r': // Right
                currentDirection = Direction.RIGHT;
                break;
        }
    }

    // Method to move the snake
    private void moveSnake() {
        Position head = snake.get(0); // Get the snake's head position
        int newX = head.x + currentDirection.xOffset; // Calculate new X coordinate
        int newY = head.y + currentDirection.yOffset; // Calculate new Y coordinate
        Position newHead = new Position(newX, newY); // Create new head position
        snake.add(0, newHead); // Add new head to the snake

        if (!newHead.equals(food)) { // Check if snake eats food
            snake.remove(snake.size() - 1); // If not, remove the last segment of the snake
        } else {
            placeFood(); // If yes, place new food
        }
    }

    // Method to check for collisions
    private void checkCollision() {
        Position head = snake.get(0); // Get snake's head position
        if (head.x < 0 || head.x >= width || head.y < 0 || head.y >= height || snake.indexOf(head) != 0) {
            isGameOver = true; // Check if snake collides with itself or boundaries
        }
    }

    // Method to place food on the grid
    private void placeFood() {
        int foodX = random.nextInt(width); // Generate random X coordinate for food
        int foodY = random.nextInt(height); // Generate random Y coordinate for food
        food = new Position(foodX, foodY); // Create food position
    }

    // Enum representing directions
    private enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        int xOffset, yOffset;

        Direction(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }
    }

    // Class representing position on the grid
    private class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Position position = (Position) obj;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }
}
