package com.training.day34;

/**
 * @author ycb
 * @since 2021/10/22-9:22
 */
public class Problem_0289_GameOfLife {

    public static void gameOfLife(int[][] board) {
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int neighbors = neighbors(board, i, j);
                if (neighbors == 3 || (board[i][j] == 1 && neighbors == 2)) {
                    board[i][j] |= 2;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    public static int neighbors(int[][] board, int i, int j) {
        return func(board, i + 1, j) + func(board, i - 1, j) + func(board, i, j + 1) + func(board, i, j - 1)
                + func(board, i - 1, j - 1) + func(board, i - 1, j + 1) + func(board, i + 1, j - 1) + func(board, i + 1, j + 1);
    }

    public static int func(int[][] arr, int i, int j) {
        return (i >= 0 && i < arr.length && j >= 0 && j < arr[0].length && (arr[i][j] & 1) == 1) ? 1 : 0;
    }
}
