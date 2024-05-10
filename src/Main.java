import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        Menu menu = new Menu();
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        OneThreadSolutionsGenerator solutionGenerator = new OneThreadSolutionsGenerator();

        while (true) {
            choice = menu.displayMainMenu();
            switch (choice) {
                case 1:
                    int[][] sudoku = menu.provideSudokuManually();
                    menu.displaySudoku(sudoku);
                    break;
                case 2:
                    sudoku = sudokuGenerator.generateSudoku(40);
                    menu.displaySudoku(sudoku);
                    ArrayList<int[][]> solutions = solutionGenerator.findSolutions(sudoku);
                    menu.displaySolutions(solutions);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}