import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }

        char player = 'X';
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard(board);
            System.out.println("Player " + player + " enter row and column (0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            System.out.println();

            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                if (board[row][col] == ' ') {
                    board[row][col] = player;
                    gameOver = haveWon(board, player);
                    if (gameOver) {
                        printBoard(board);
                        System.out.println("Player " + player + " has won!");
                    } else {
                        player = (player == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("Invalid move. Try again!");
                }
            } else {
                System.out.println("Invalid input. Row and column must be between 0 and 2.");
            }
        }
    }

    public static boolean haveWon(char[][] board, char player) {
        // Check rows
        for (char[] row : board) {
            boolean win = true;
            for (char cell : row) {
                if (cell != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            boolean win = true;
            for (char[] chars : board) {
                if (chars[col] != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // Check diagonals
        boolean diagonal1Win = true;
        boolean diagonal2Win = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] != player) {
                diagonal1Win = false;
            }
            if (board[i][2 - i] != player) {
                diagonal2Win = false;
            }
        }

        return diagonal1Win || diagonal2Win;
    }

    public static void printBoard(char[][] board) {
        System.out.println("   0   |     1    |     2    |");
        for (int row = 0; row < 3; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + "    |     ");
            }
            System.out.println();
            if (row < 2) {
                System.out.println("   ...........................");
            }
        }
    }
}
