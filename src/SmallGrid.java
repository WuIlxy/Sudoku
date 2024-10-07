package org.cis1200.Sudoku;

import javax.swing.*;
import java.awt.*;

public class SmallGrid extends JPanel {
    private final int row;
    private final int col;
    private final GridLayout grid = new GridLayout(3, 3);

    public SmallGrid(int row, int col) {
        this.row = row;
        this.col = col;
        init();
    }

    private void init() {
        setLayout(grid);
        int startRow = this.row * 3 + 1;
        int startCol = this.col * 3 + 1;
        for (int i : new int[]{0, 1, 2}) {
            for (int j : new int[]{0, 1, 2}) {
                int row = startRow + i;
                int col = startCol + j;
                if (GenerateGame.getSudoku().isEditable(row, col)) {
                    add(new CellEditor(row, col));
                } else {
                    add(new CellPresenter(row, col));
                }
            }
        }
    }
}