/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight.queens;

/**
 *
 * @author marco
 */
public class heuristic {

    public boolean colCon(int[][] grid, int a) {
        boolean confict = false;
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (grid[a][i] == 1) {
                count++;
            }
        }
        if (count > 1) {
            confict = true;
        }
        return confict;
    }

    //row conflicts
    public boolean rowCon(int[][] grid, int a) {
        boolean conflict = false;
        int count = 0;

        for (int i = 0; i < 8; i++) {
            if (grid[i][a] == 1) {
                count++;
            }
        }
        if (count > 1) {
            conflict = true;
        }
        return conflict;
    }

    //diagonal conflicts
    public boolean dialCon(int[][] grid, int a, int b) {
        boolean conflict = false;

        for (int i = 1; i < 8; i++) {

            if ((a + i < 8) && (b + i < 8)) {
                if (grid[a + i][b + i] == 1) {
                    conflict = true;
                }
            }
            if ((a + i < 8) && (b - i >= 0)) {
                if (grid[a + i][b - i] == 1) {
                    conflict = true;
                }
            }
            if ((a - i >= 0) && (b - i >= 0)) {
                if (grid[a - i][b - i] == 1) {
                    conflict = true;
                }
            }

            if ((a - i >= 0) && (b + i < 8)) {
                if (grid[a - i][b + i] == 1) {
                    conflict = true;
                }
            }
        }
        return conflict;
    }

    /* public boolean con(int [][] q, int n){
        for (int i = 0; i < 8; i++) {
            if()
        }
        return false;
    }*/
    
    //returns number of queens in conflict
    public int heuristic(int[][] grid) {
        int count = 0;
        boolean colCon;
        boolean rowCon;
        boolean dialCon;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == 1) {
                    colCon = colCon(grid, i);
                    rowCon = rowCon(grid, j);
                    dialCon = dialCon(grid, i, j);

                    if (colCon || rowCon || dialCon) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
