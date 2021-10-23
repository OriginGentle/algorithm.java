package com.training.day34;

/**
 * @author ycb
 * @since 2021/10/22-9:24
 */
public class Problem_0348_DesignTicTacToe {

    class TicTacToe {
        private int[][] rows;
        private int[][] cols;
        private int[] leftUp;
        private int[] rightUp;
        private boolean[][] matrix;
        private int N;

        public TicTacToe(int n) {
            // rows[a][1] : 1这个人，在a行上，下了几个
            rows = new int[n][3];
            cols = new int[n][3];
            // leftUp[2] = 7 : 2这个人，在左对角线上，下了7个
            leftUp = new int[3];
            // rightUp[1] = 9 : 1这个人，在右对角线上，下了9个
            rightUp = new int[3];
            matrix = new boolean[n][n];
            N = n;
        }

        public int move(int row, int col, int player) {
            if (matrix[row][col]) {
                return 0;
            }
            matrix[row][col] = true;
            rows[row][player]++;
            cols[col][player]++;
            if (row == col) {
                leftUp[player]++;
            }
            if (row + col == N - 1) {
                rightUp[player]++;
            }
            if (rows[row][player] == N || cols[col][player] == N || leftUp[player] == N || rightUp[player] == N) {
                return player;
            }
            return 0;
        }

    }
}
