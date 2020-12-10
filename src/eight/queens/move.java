/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight.queens;

import java.util.Random;

/**
 *
 * @author marco
 */
public class move {

    heuristic heur = new heuristic();

    final public int[][] board = new int[8][8];
    final public int[][] board2 = new int[8][8];
    public int countRestarts = 0;
    public int countMoves = 0;
    public int conflicts = 0;
    public int neighbors = 8;
    public int queen = 0;

    public void randomMap() {
        Random rand = new Random();
        while (queen < 8) {
            for (int i = 0; i < 8; i++) {
                board[rand.nextInt(7)][i] = 1;
                queen++;
            }
        }
        conflicts = heur.heuristic(board);
    }

    public boolean restart(int[][] grid) {
        int minVal = 8;
        boolean restart = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] < minVal) {
                    minVal = grid[i][j];
                }
            }
        }
        if (neighbors == 0) {
            restart = true;
        }
        return restart;
    }

    public int minCol(int[][] grid) {
        int minVal = 8;
        int minCol = 8;
        int count = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] < minVal) {
                    minVal = grid[i][j];
                    minCol = j;
                }
                if (grid[i][j] < conflicts) {
                    count++;
                }
            }
        }
        neighbors = count;
        return minCol;
    }

    public int minRow(int[][] grid) {
        int minVal = 8;
        int minRow = 8;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] < minVal) {
                    minVal = grid[i][j];
                    minRow = i;
                }
            }
        }
        return minRow;
    }

    public void moveQueen() {
        int[][] grid = new int[8][8];
        int minCol;
        int minRow;
        int colCount;
        int prevCol = 0;

        while (true) {
            colCount = 0;

            while (colCount < 8) {
                for (int i = 0; i < 8; i++) {
                    board2[i][colCount] = 0;
                }
                for (int i = 0; i < 8; i++) {
                    if (board[i][colCount] == 1) {
                        prevCol = i;
                    }
                    board2[i][colCount] = 1;
                    grid[i][colCount] = heur.heuristic(board2);
                    board2[i][colCount] = 0;
                }
                board2[prevCol][colCount] = 1;
                colCount++;
            }

            if (restart(grid)) {
                queen = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        board[i][j] = 0;
                    }
                }
                randomMap();
                System.out.println("RESTART");
                countRestarts++;
            }

            minRow = minRow(grid);
            minCol = minCol(grid);

            for (int i = 0; i < 8; i++) {
                board[i][minCol] = 0;
            }

            board[minRow][minCol] = 1;
            countMoves++;
            conflicts = heur.heuristic(board);

            if (heur.heuristic(board) == 0) {
                System.out.println("\nCurrent State");
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(board[i][j]);
                    }
                    System.out.print("\n");
                }
                System.out.println("Solution Found!");
                System.out.println("State changes: " + countMoves);
                System.out.println("Restarts: " + countRestarts);
                break;
            }

            System.out.println("\nCurrent h: " + conflicts);
            System.out.println("Current State:");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.print("\n");
            }
            System.out.println("Neighbors found with lower h: " + neighbors);
            System.out.println("Setting new current State");
        }
    }

}
