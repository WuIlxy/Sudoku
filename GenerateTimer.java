package org.cis1200.Sudoku;

import javax.swing.*;

public class GenerateTimer extends JLabel {
    private final Timer timer;

    GenerateTimer() {
        timer = new Timer(1000, e -> {
            setText(GenerateGame.getTime());
        });
        setFont(Frame.toolFont);
    }

    void init() {
        timer.start();
        setText(GenerateGame.getTime());
    }

    void stop() {
        timer.stop();
    }
}
