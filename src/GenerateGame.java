package org.cis1200.Sudoku;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class GenerateGame {
    private static Frame frame;
    private static SudokuGenerator sudokuGenerator;

    /* This sets the difficulty of the generated sudoku
    * It will be in the range from 0 to 1
    * The higher the number is, the lower the difficulty
     */

    private static final double PERCENT = 0.40;

    public static SudokuGenerator getSudoku() {
        return sudokuGenerator;
    }

    // create the game
    public GenerateGame() {
        GameTimer.init();
        sudokuGenerator = new SudokuGenerator(PERCENT);
        frame = new Frame();
    }

    //display the ranking txt file to a new panel
    public static void displayRanking() {
        ArrayList<PlayerInfo> playerInfos = readPlayerInfoFromFile();
        Collections.sort(playerInfos, Comparator.comparingLong(PlayerInfo::time));
        StringBuilder sb = new StringBuilder("Ranking: \n");
        int rank = 1;
        for (PlayerInfo playerInfo : playerInfos) {
            sb.append(rank).append(". ").append(playerInfo.name())
                    .append(" - ").append(playerInfo.time()).append(" milliseconds\n");
            rank++;
        }
        JOptionPane.showConfirmDialog(null, sb.toString(), "Ranking",
                JOptionPane.YES_NO_CANCEL_OPTION);
    }

    // helper method for reading the ranking file
    private static ArrayList<PlayerInfo> readPlayerInfoFromFile() {
        ArrayList<PlayerInfo> playerInfos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(
                "src/main/java/org/cis1200/Sudoku/Ranking"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0].trim();
                    long timeString = Long.parseLong(parts[1].trim());
                    playerInfos.add(new PlayerInfo(playerName, timeString));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return playerInfos;
    }

    // generate new game
    public static void next() {
        GameTimer.init();
        sudokuGenerator.next();
        frame.next();
    }

    // reveal answer
    public static void solve() {
        sudokuGenerator.solve(0);
        frame.stop();
    }

    // getter for time
    public static String getTime() {
        String gameTime = GameTimer.getTime();
        GameTimer.updateTime();
        return gameTime;
    }
}