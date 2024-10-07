package org.cis1200.Sudoku;

import javax.swing.*;
import java.awt.*;

public class CellPresenter extends JLabel {
    public CellPresenter(int row, int col) {
        super("", JLabel.CENTER);

        SudokuGenerator sudoku = GenerateGame.getSudoku();
        int number = sudoku.getNum(row, col);

        if (number != 0) {
            setText(String.valueOf(number));
            setForeground(Color.black);
        } else {
            setText("");
            setForeground(Color.lightGray);
        }

        setFont(Frame.numberFont);
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
}