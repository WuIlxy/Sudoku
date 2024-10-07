package org.cis1200.Sudoku;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuTest {

    @Test
    public void testSolve() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        assertTrue(s.solve(0));
    }

    @Test
    public void testRows() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        s.solve(0);
        int sum = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                sum += s.getNum(i, j);
            }
        }
        assertEquals(405, sum);
    }

    @Test
    public void testCols() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        s.solve(0);
        int sum = 0;
        for (int j = 1; j <= 9; j++) {
            for (int i = 1; i <= 9; i++) {
                sum += s.getNum(i, j);
            }
        }
        assertEquals(405, sum);
    }

    @Test
    public void testSquares() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        s.solve(0);
        int[][] temp = new int[9][9];
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                temp[i - 1][j - 1] = s.getNum(i, j);
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                assertTrue(isValidSquare(temp, i, j));
            }
        }
    }


    private static boolean isValidSquare(int[][] board, int startRow, int startCol) {
        int squareSum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squareSum += board[startRow + i][startCol + j];
            }
        }
        return squareSum == 45;
    }
    
    @Test 
    public void testUnsolvedRow() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        boolean hasZero = false;
        for (int i = 1; i <= 9; i++) {
            if (s.getNum(i, 1) == 0) {
                hasZero = true;
            }
        }
        assertTrue(hasZero);
    }

    @Test
    public void testUnsolvedCol() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        boolean hasZero = false;
        for (int j = 1; j <= 9; j++) {
            if (s.getNum(1, j) == 0) {
                hasZero = true;
            }
        }
        assertTrue(hasZero);
    }

    @Test
    public void testUnsolvedSquare() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        boolean hasZero = false;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (s.getNum(i, j) == 0) {
                    hasZero = true;
                }
            }
        }
        assertTrue(hasZero);
    }

    @Test
    public void testIsCompleted() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        assertFalse(s.isCompleted());
        s.solve(0);
        assertTrue(s.isCompleted());
    }

    @Test
    public void testIsEditable() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        int row = 0;
        int col = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (s.getNum(i, j) == 0) {
                    row = i;
                    col = j;
                }
            }
        }
        assertTrue(s.isEditable(row, col));
    }
    @Test
    public void testUpdateCell() {
        SudokuGenerator s = new SudokuGenerator(0.36);
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (s.isEditable(i, j)) {
                    s.updateNum(i, j, 10);
                    assertEquals(10, s.getNum(i, j));
                }
            }
        }
    }
    
}
