import java.util.*;

public class SudokuGenerator {
    private int[][] board;
    private static final int SIZE = 9;
    private static final int UNASSIGNED = 0;
    private final Random random = new Random();

    public int[][] generateSudoku(int holes) {
        boolean isFilledCorrectly = false;

        do {
            board = new int[SIZE][SIZE];
            fillDiagonalBoxes();
            isFilledCorrectly = fillRemainingBoxes();
        } while (!isFilledCorrectly);

        removeNumbers(holes);
        return board;
    }

    private void fillDiagonalBoxes() {
        for (int i = 0; i < SIZE; i += 3) {
            fillBox(i, i);
        }
    }

    private void fillBox(int row, int col) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[row + i][col + j] = numbers.get(i * 3 + j);
            }
        }
    }

    private boolean fillRemainingBoxes() {
        for (int row = 0; row < SIZE; row += 3) {
            for (int col = 0; col < SIZE; col += 3) {
                // Sprawdzanie czy kwadrat jest jednym z trzech początkowych wypełnionych po przekątnej
                if ((row == 0 && col == 0) || (row == 3 && col == 3) || (row == 6 && col == 6)) {
                    continue; // Pomija kwadraty wypełnione już na przekątnej
                }
                if (!fillBoxSafely(row, col)) {
                    return false; // Nie udało się bezpiecznie wypełnić kwadratu
                }
            }
        }
        return true;
    }

    private boolean fillBoxSafely(int row, int col) {
        List<Integer> possibleNumbers;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                possibleNumbers = getPossibleNumbers(row + i, col + j);
                if (possibleNumbers.isEmpty()) {
                    return false; // Brak możliwych liczb do wstawienia
                }
                Collections.shuffle(possibleNumbers);
                board[row + i][col + j] = possibleNumbers.get(0); // Wybór losowej liczby
            }
        }
        return true;
    }

    private List<Integer> getPossibleNumbers(int row, int col) {
        Set<Integer> possibleNumbers = new HashSet<>();
        for (int num = 1; num <= SIZE; num++) {
            possibleNumbers.add(num);
        }

        // Usuwanie liczb z rzędu
        for (int j = 0; j < SIZE; j++) {
            possibleNumbers.remove(board[row][j]);
        }

        // Usuwanie liczb z kolumny
        for (int i = 0; i < SIZE; i++) {
            possibleNumbers.remove(board[i][col]);
        }

        // Usuwanie liczb z kwadratu
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                possibleNumbers.remove(board[startRow + i][startCol + j]);
            }
        }

        return new ArrayList<>(possibleNumbers);
    }

    private void removeNumbers(int holes) {
        int count = holes;
        while (count > 0) {
            int i = random.nextInt(SIZE);
            int j = random.nextInt(SIZE);
            if (board[i][j] != UNASSIGNED) {
                board[i][j] = UNASSIGNED;
                count--;
            }
        }
    }
}
