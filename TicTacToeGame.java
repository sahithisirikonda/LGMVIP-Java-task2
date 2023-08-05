import java.util.Scanner;

public class TicTacToeGame {
    private char[][] board;
    private char currentPlayer;
    private boolean isGameOver;

    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        isGameOver = false;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return true; // Row win
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return true; // Column win
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return true; // Diagonal win
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return true; // Diagonal win
        }

        return false;
    }

    private boolean checkDraw() {
        // Check if the board is full (draw condition)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            displayBoard();

            System.out.println("Player " + currentPlayer + ", enter your move (row [0-2] and column [0-2]):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                makeMove(row, col);

                if (checkWin()) {
                    displayBoard();
                    System.out.println("Player " + ((currentPlayer == 'X') ? 'O' : 'X') + " wins!");
                    isGameOver = true;
                } else if (checkDraw()) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    isGameOver = true;
                }
            } else {
                System.out.println("Invalid move! Please try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
