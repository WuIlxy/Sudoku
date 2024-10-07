package org.cis1200.Sudoku;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    private GenerateTimer generateTimer;

    public ToolBar() {
        start();
    }

    private void start() {
        // Instructions for new players
        JButton bTutorial = new JButton("Tutorial");
        bTutorial.setFont(Frame.toolFont);
        bTutorial.setForeground(Color.BLACK);
        bTutorial.setBackground(Color.lightGray);
        bTutorial.addActionListener(e -> {
            openTutorialWindow();
        });
        add(bTutorial);
        // Initialize a new game (board)
        JButton bNext = new JButton("New");
        bNext.setFont(Frame.toolFont);
        bNext.setForeground(Color.BLACK);
        bNext.setBackground(Color.lightGray);
        bNext.addActionListener(e -> {
            GenerateGame.next();
        });
        add(bNext);
        // Solve and reveal the answer
        JButton bSolve = new JButton("Answer");
        bSolve.setFont(Frame.toolFont);
        bSolve.setForeground(Color.BLACK);
        bSolve.setBackground(Color.lightGray);
        bSolve.addActionListener(e -> {
            GenerateGame.solve();
        });
        add(bSolve);
        // Display the ranking (with name and time)
        JButton bRank = new JButton("Rank");
        bRank.setFont(Frame.toolFont);
        bRank.setForeground(Color.BLACK);
        bRank.setBackground(Color.lightGray);
        bRank.addActionListener(e -> {
            generateTimer.stop();
            GenerateGame.displayRanking();
        });
        add(bRank);
        // Add game time
        generateTimer = new GenerateTimer();
        generateTimer.init();
        add(generateTimer);
    }

    private void openTutorialWindow() {
        generateTimer.stop();
        String tutorialMessage = "Welcome to Sudoku! \n" +
                "Sudoku is a popular number puzzle game that involves filling a 9x9 grid " +
                "with numbers\n" +
                "so that each column, each row, and each of the nine 3x3 sub-grids contain " +
                "all of the\n" +
                "digits from 1 to 9. \n" +
                "Instructions: \n" +
                "1. Sudoku is played on a 9x9 grid.\n" +
                "The grid is divided into nine 3x3 sub-grids, creating a total of 81 cells. \n" +
                "2. The goal is to fill in the entire grid with numbers from 1 to 9 according " +
                "to the rules.\n" +
                "3. Each row must contain all the numbers from 1 to 9 with no repetition. \n" +
                "Each column must contain all the numbers from 1 to 9 with no repetition. \n" +
                "Each of the nine 3x3 sub-grids must contain all the numbers from 1 to 9 with " +
                "no repetition.\n" +
                "4. A Sudoku puzzle begins with some cells already filled in with numbers. \n" +
                "5. To solve the puzzle, you must fill in the empty cells with numbers from 1 " +
                "to 9, \n" +
                "following the rules. \n" +
                "6. You can only place a number if it doesn't violate the rules of the game. \n" +
                "Making too many mistakes will terminate the game and ask for a start. \n" +
                "7. Once you make a mistake on the board, the filled in number will turn red.\n" +
                "You are allowed to erase that entry and continue to work on your game.\n" +
                "8. To get a new puzzle to play with, you can use the 'new' button. \n" +
                "To get the answer immediately, you can also use the 'answer' button.\n" +
                "9. Once you are able to finish the game, you will be asked to enter your " +
                "name. \n" +
                "You can use the 'rank' button to check your ranking against other players. \n"  +
                "10. Enjoy and have fun!"
                ;
        JOptionPane.showMessageDialog(null, tutorialMessage, "Sudoku Tutorial",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void next() {
        stop();
        generateTimer.init();
    }

    public void stop() {
        generateTimer.stop();
    }
}

