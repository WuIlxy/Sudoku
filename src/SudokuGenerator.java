package org.cis1200.Sudoku;

import java.util.Random;

public class SudokuGenerator {
    private Cell[][] board; // the actual sudoku game board
    private Cell[] empty; // the array for all the empty numbers
    private int hidden; // the number of hidden cells
    private boolean solvable; // check if it can be fulfilled with numbers under the rule
    private double difficulty;

    private static class Cell {
        int row;
        int col;
        int num;
        boolean editable;

        Cell(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num; //for cells with number presented
            this.editable = false;
        }

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
            this.num = 0; //for empty cells
            this.editable = true;
        }
    }

    // constructor with just the difficulty
    public SudokuGenerator(double difficulty) {
        this.difficulty = difficulty;
        next();
    }

    // update with another sudoku
    private SudokuGenerator(Cell[][] puzzle) {
        update(puzzle);
    }

    // initialize the cells
    private void init() {
        board = new Cell[11][11];
        empty = new Cell[81];
        hidden = 0;
        solvable = false;
    }

    // randomly generate the board by fulfilling some cells based on the difficulty value
    public void next() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int reveal = (int) Math.ceil(difficulty * 81);
        do {
            init();
            for (int num = 0; num < reveal; num++) {
                int row = rand.nextInt(9) + 1;
                int col = rand.nextInt(9) + 1;
                int count = 0;
                if (board[row][col] == null || board[row][col].num == 0) {
                    do {
                        board[row][col] = new Cell(row, col, rand.nextInt(9) + 1);
                        update(board);
                        count++;
                        if (count == 9) {
                            board[row][col] = null;
                            update(board);
                            num--;
                            break;
                        }
                    } while (!check(row, col));
                } else {
                    num--;
                }
            }
            isSolvable();
        } while (!solvable);
    }

    // getter method for each cell
    public int getNum(int row, int col) {
        return board[row][col].num;
    }

    // determine editable
    public boolean isEditable(int row, int col) {
        return board[row][col].editable;
    }

    // method for updating each cell
    public void updateNum(int row, int col, int num) {
        board[row][col].num = num;
    }

    // check if the solve function can actually solve the board (should not cause problem)
    private void isSolvable() {
        SudokuGenerator s = new SudokuGenerator(board);
        s.solve(0);
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (s.board[i][j].num != board[i][j].num) {
                    solvable = true;
                    return;
                }
            }
        }
        solvable = false;
    }

    // solve the board (recursion)
    public boolean solve(int value) {
        if (value == hidden) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            updateNum(empty[value].row, empty[value].col, i);
            if (check(empty[value].row, empty[value].col) && solve(value + 1)) {
                return true;
            }
        }
        updateNum(empty[value].row, empty[value].col, 0);
        return false;
    }

    // helper method to check if a filled number is valid or not
    public boolean check(int row, int col) {
        for (int i = 1; i <= 9; i++) {
            if (i != row && board[row][col].num == board[i][col].num) { //check entire col
                return false;
            }
            if (i != col && board[row][col].num == board[row][i].num) { //check entire row
                return false;
            }
        }
        for (int i = (row - 1) / 3 * 3 + 1; i <= (row + 2) / 3 * 3; i++) { //check 3x3 subgrid
            for (int j = (col - 1) / 3 * 3 + 1; j <= (col + 2) / 3 * 3; j++) {
                if (i != row && j != col && board[row][col].num == board[i][j].num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void update(Cell[][] puzzle) {
        init();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                board[i][j] = puzzle[i][j];
                if (board[i][j] == null || board[i][j].num == 0) {
                    board[i][j] = new Cell(i, j);
                    empty[hidden] = board[i][j];
                    hidden++;
                }
            }
        }
    }

    // whether completed or not
    public boolean isCompleted() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (board[i][j].num == 0 || !check(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}