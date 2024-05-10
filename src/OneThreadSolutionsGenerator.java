import java.util.ArrayList;

public class OneThreadSolutionsGenerator {
    private static final int SIZE = 9;
    ArrayList<int[][]> solutions;

    public ArrayList<int[][]> findSolutions(int[][] board) {
        solutions = new ArrayList<>();
        solveSudoku(board);
        return solutions;
    }

    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                copySolution(board);
                                return false;
                            } else {
                                board[row][col] = 0;  // Backtrack
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;  // Sudoku solved
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void copySolution(int[][] board) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, SIZE);
        }
        solutions.add(copy);
    }
}
