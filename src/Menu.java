import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
        System.out.println("Welcome to the Sudoku generator and resolver!");
    }

    public int displayMainMenu() {
        // display main menu and wait for response
        // clearScreen();
        System.out.println("1. Provide sudoku manually");
        System.out.println("2. Generate a random sudoku");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Błąd podczas czyszczenia konsoli: " + e.getMessage());
        }
    }

    public int[][] provideSudokuManually() {
        // provide sudoku manually
        int[][] sudoku = new int[9][9];

        System.out.println("Enter the sudoku row by row. Use 0 for empty cells.");
        for (int i = 0; i < 9; i++) {
            System.out.print("Enter row " + (i + 1) + ": ");
            for (int j = 0; j < 9; j++) {
                try {
                    int num = scanner.nextInt();
                    if (num < 0 || num > 9) {
                        System.out.println("Invalid input.");
                        j--;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                    scanner.nextLine();
                    j--;
                }
            }
        }

        return sudoku;
    }

    public void displaySudoku(int[][] sudoku) {
        // display the sudoku
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println(" -----------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(sudoku[i][j] == 0 ? " " : sudoku[i][j]);
                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    public void displaySolutions(ArrayList<int[][]> solutions) {
        // display the solutions
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            displaySudoku(solutions.get(i));
        }
    }
}
