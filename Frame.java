package org.cis1200.Sudoku;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final LargeGrid largeGrid;
    private final ToolBar toolBar;
    static Font numberFont = new Font("Open Sans", Font.BOLD, 35);
    static Font toolFont = new Font("Comic Sans MS", Font.PLAIN, 15);

    public Frame() {
        largeGrid = new LargeGrid();
        toolBar = new ToolBar();
        init();
    }

    private void init() {
        add(largeGrid.getPanel(), BorderLayout.CENTER);
        add(toolBar, BorderLayout.SOUTH);
        setTitle("Sudoku");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void next() {
        largeGrid.next();
        toolBar.next();
    }

    public void stop() {
        largeGrid.next();
        toolBar.stop();
    }
}