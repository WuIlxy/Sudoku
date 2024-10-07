package org.cis1200.Sudoku;

import javax.swing.*;
import java.awt.*;

public class LargeGrid {

    private final JPanel panel = new JPanel();
    private final GridLayout grid = new GridLayout(3, 3);

    public LargeGrid() {
        init();
    }

    private void init() {
        panel.setLayout(grid);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SmallGrid smallGrid = new SmallGrid(i, j);
                smallGrid.setBackground(Color.lightGray);
                smallGrid.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
                panel.add(smallGrid);
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public void next() {
        panel.removeAll();
        init();
        panel.revalidate();
        panel.repaint();
    }
}