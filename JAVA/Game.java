import java.util.*;

// Class representing the Tic-Tac-Toe game logic
class TicTacToe {
    static char[][] board; // 2D array to represent the game board

    // Constructor to initialize the board
    public TicTacToe() {
        board = new char[3][3]; // Initialize 3x3 board
        initBoard(); // Call method to initialize the board with empty spaces
    }

    // Method to initialize the board with empty spaces
    void initBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = ' '; // Set each cell to empty space
            }
        }
    }

    // Method to display the current state of the board
    static void dispBoard() {
        System.out.println(" - - - - - -");
        for(int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | "); // Print cell value with border
            }
            System.out.println();
            System.out.println(" - - - - - -");
        }
    }

    // Method to place a mark (X or O) on the board at given coordinates
    static void placeMark(int row, int col, char mark) {
        if(row >= 0 && row <= 2 && col >=0 && col <= 2) {
            board[row][col] = mark; // Set the specified cell to the given mark
        }
        else {
            System.out.println("Invalid Position"); // Print error message for invalid coordinates
        }
    }

    // Method to check if any player has won by completing a column
    static boolean checkColWin() {
        for(int j = 0; j <= 2; j++) {
            if(board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true; // Return true if a column is filled with the same mark
            }
        }
        return false; // Return false if no column is filled with the same mark
    }

    // Method to check if any player has won by completing a row
    static boolean checkRowWin() {
        for(int i = 0; i <= 2; i++) {
            if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true; // Return true if a row is filled with the same mark
            }
        }
        return false; // Return false if no row is filled with the same mark
    }

    // Method to check if any player has won by completing a diagonal
    static boolean checkDiagWin() {
        if((board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) || 
           (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return true; // Return true if a diagonal is filled with the same mark
        }
        return false; // Return false if no diagonal is filled with the same mark
    }
}

// Class representing a human player
class HumanPlayer {
    String name; // Player's name
    char mark; // Player's mark (X or O)

    // Constructor to initialize player's name and mark
    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    // Method for the player to make a move
    void makeMove() {
        Scanner sc = new Scanner(System.in); // Scanner for user input
        int row;
        int col;
        do {
            System.out.println("Enter the row and col"); // Prompt user for row and column input
            row = sc.nextInt(); // Read row input
            col = sc.nextInt(); // Read column input
        } while (!isValidMove(row, col)); // Repeat until a valid move is made

        TicTacToe.placeMark(row, col, mark); // Place the player's mark on the board
    }

    // Method to check if a move is valid
    boolean isValidMove(int row, int col) {
        if(row >= 0 && row <= 2 && col >= 0 && col <= 2) { // Check if coordinates are within bounds
            if(TicTacToe.board[row][col] == ' ') { // Check if the cell is empty
                return true; // Return true if the move is valid
            }
        }
        return false; // Return false if the move is invalid
    }
}

// Main class to run the Tic-Tac-Toe game
public class Game {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in); // Scanner for user input
        TicTacToe t = new TicTacToe(); // Create an instance of the TicTacToe game
        System.out.println("Enter player 1 name"); // Prompt for player 1's name
        HumanPlayer p1 = new HumanPlayer(sc.nextLine(), 'X'); // Create player 1 with mark 'X'
        System.out.println("Enter player 2 name"); // Prompt for player 2's name
        HumanPlayer p2 = new HumanPlayer(sc.nextLine(), 'O'); // Create player 2 with mark 'O'
        System.out.println(); // Print empty line

        HumanPlayer cp; // Current player
        cp = p1; // Start with player 1

        while(true) {
            System.out.println(cp.name + " turn"); // Print current player's turn
            cp.makeMove(); // Current player makes a move
            TicTacToe.dispBoard(); // Display updated board

            // Check for win conditions
            if(TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
                System.out.println(cp.name + " has won"); // Print winner
                break; // End game
            }
            else {
                if(cp == p1) {
                    cp = p2; // Switch to player 2's turn
                }
                else {
                    cp = p1; // Switch to player 1's turn
                }
            }
        }
    }
}
