package org.cis1200.Sudoku;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CellEditor extends JTextField {

    private static final int EMPTY_CELL = 0;
    private static int errors = 0;
    public CellEditor(int row, int col) {
        int number = GenerateGame.getSudoku().getNum(row, col);
        if (number != EMPTY_CELL) {
            setText(String.valueOf(GenerateGame.getSudoku().getNum(row, col)));
        }
        setFont(Frame.numberFont);
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
        setRightColor();
        setHorizontalAlignment(JTextField.CENTER);
        getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (getDocument().getLength() > 1) {
                    GenerateGame.getSudoku().updateNum(row, col, EMPTY_CELL);
                    setWrongColor();
                    errors++;
                } else {
                    changedUpdate(e);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (getDocument().getLength() == 0) {
                    GenerateGame.getSudoku().updateNum(row, col, EMPTY_CELL);
                    setRightColor();
                } else {
                    changedUpdate(e);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (errors > 5) {
                    GenerateGame.solve();
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Sorry, you have reached the maximum number of errors. \n" +
                                    "Better lucky next time!",
                            "Sudoku Failed", JOptionPane.DEFAULT_OPTION);
                    if (choice == 0) {
                        errors = 0;
                        GenerateGame.next();
                    }
                }
                try {
                    int value = Integer.parseInt(getDocument().getText(0, 1));
                    GenerateGame.getSudoku().updateNum(row, col, value);
                    if (GenerateGame.getSudoku().check(row, col)) {
                        setRightColor();
                    } else {
                        throw new Exception();
                    }
                } catch (Exception er) {
                    GenerateGame.getSudoku().updateNum(row, col, EMPTY_CELL);
                    setWrongColor();
                    errors++;
                    return;
                }
                if (GenerateGame.getSudoku().isCompleted()) {
                    GenerateGame.solve();
                    String playerName = JOptionPane.showInputDialog(null,
                            "Enter your name: ");
                    if (playerName != null && !playerName.trim().isEmpty()) {
                        savePlayerInfo(playerName, GenerateGame.getTime());
                    }
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Congratulation! " +
                                    "Your time is: " + GenerateGame.getTime() + ". Another one?",
                            "Sudoku Completed",
                            JOptionPane.DEFAULT_OPTION);
                    if (choice == 0) {
                        GenerateGame.next();
                    }
                }
            }
        });
    }

    // write the name and current game time to the ranking file
    private void savePlayerInfo(String playerName, String time) {
        try (FileWriter writer = new FileWriter(
                "src/main/java/org/cis1200/Sudoku/Ranking", true)) {
            writer.write(playerName + "," + convertTimeStringToMillis(time) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // helper function that converts the time string to a long variable useful for reading the file
    private long convertTimeStringToMillis(String timeString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = dateFormat.parse(timeString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // if correct
    private void setRightColor() {
        setForeground(Color.GREEN);
        setBackground(Color.lightGray);
    }

    // if wrong
    private void setWrongColor() {
        setForeground(Color.RED);
        setBackground(Color.lightGray);
    }
}