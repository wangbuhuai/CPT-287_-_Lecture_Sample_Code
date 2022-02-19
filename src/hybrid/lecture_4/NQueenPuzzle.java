// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-18


package hybrid.lecture_4;

/** A recursive system to solve the N-Queen puzzle */
public class NQueenPuzzle {
    // Data fields
    private static final char QUEEN = 'Q';  // Character to represent a queen
    private static final char BLANK = '.';  // Character to represent blank
    private final char[][] board;  // Chessboard
    private final int numOfQueens;  // Number of queens
    private int numOfSolutions;  // Number of solutions found
    private final StringBuilder solutions;  // All the solutions of the puzzle

    // Constructors

    public NQueenPuzzle(int numOfQueens) {
        this.numOfQueens = numOfQueens;
        board = new char[this.numOfQueens][this.numOfQueens];
        for (int row = 0; row < this.numOfQueens; row++) {
            for (int col = 0; col < this.numOfQueens; col++) { board[row][col] = BLANK; }
        }
        solutions = new StringBuilder();
    }

    // Methods

    /** Tests whether there is already a queen in the same column.
        @param col: index of the column to test
        @return: {true} if there is already a queen in the same column; {false} otherwise
    */
    private boolean inSameCol(int col) {
        for (int row = 0; row < numOfQueens; row++) {
            if (board[row][col] == QUEEN) { return true; }
        }
        return false;
    }

    /** Tests whether there is already a queen in diagonal directions.
        @param row: row index of the current cell
        @param col: column index of the current cell
        @return: {true} if there is already a queen in diagonal directions; {false} otherwise
    */
    private boolean inDiagonal(int row, int col) {
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == QUEEN) { return true; }
        }
        for (int i = row, j = col; i >= 0 && j < numOfQueens; i--, j++) {
            if (board[i][j] == QUEEN) { return true; }
        }
        for (int i = row, j = col; i < numOfQueens && j < numOfQueens; i++, j++) {
            if (board[i][j] == QUEEN) { return true; }
        }
        for (int i = row, j = col; i < numOfQueens && j >= 0; i++, j--) {
            if (board[i][j] == QUEEN) { return true; }
        }
        return false;
    }

    /** Generates a single numbered solution.
        @return: a string representing a single numbered solution
    */
    private String singleSolution() {
        StringBuilder builder = new StringBuilder().append("Solution ").append(++numOfSolutions).append("\n\n");
        for (int row = 0; row < numOfQueens; row++) {
            for (int col = 0; col < numOfQueens; col++) { builder.append(board[row][col]); }
            builder.append('\n');
        }
        return builder.append('\n').toString();
    }

    /** Recursively generates all the solutions of the puzzle.
        @param row: row index to place the next queen
    */
    private void allSolutions(int row) {
        if (row == numOfQueens) { solutions.append(singleSolution()); }  // Base case
        else {
            for (int col = 0; col < numOfQueens; col++) {
                if (!inSameCol(col) && !inDiagonal(row, col)) {
                    board[row][col] = QUEEN;
                    allSolutions(row + 1);  // Recursion
                }
                board[row][col] = BLANK;
            }
        }
    }

    /** Solves the puzzle.
        @return: a string representing all the solutions of the puzzle.
    */
    public String solve() {
        allSolutions(0);
        return solutions.toString();
    }
}