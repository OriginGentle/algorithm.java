package com.system.III_training.day03;

/**
 * @Author ycb
 * @Date 2021/7/29-11:14
 * @Description https://leetcode.com/problems/largest-1-bordered-square/
 */
public class Code03_Largest1BorderedSquare {

    // 数组预处理
    public static int largest1BorderedSquare(int[][] m) {
        // 记录当前位置,往右(包括自己在内)有多少个连续的 1
        int[][] right = new int[m.length][m[0].length];
        // 记录当前位置,往下(包括自己在内)有多少个连续的 1
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        // 枚举所有的边长(从大到小)看看是否能够构成正方形
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            if (hasSizeBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }

    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int row = m.length;
        int col = m[0].length;
        if (m[row - 1][col - 1] == 1) {
            right[row - 1][col - 1] = 1;
            down[row - 1][col - 1] = 1;
        }
        // 最后一列的信息填好
        for (int i = row - 2; i != -1; i--) {
            if (m[i][col - 1] == 1) {
                right[i][col - 1] = 1;
                down[i][col - 1] = down[i + 1][col - 1] + 1;
            }
        }
        // 最后一行的信息填好
        for (int i = col - 2; i != -1; i--) {
            if (m[row - 1][i] == 1) {
                down[row - 1][i] = 1;
                right[row - 1][i] = right[row - 1][i + 1] + 1;
            }
        }
        // 把剩下位置的信息填好
        for (int i = row - 2; i != -1; i--) {
            for (int j = col - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public static boolean hasSizeBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                // 有了边长，需要两个点才能确定一个正方形
                if (right[i][j] >= size && down[i][j] >= size
                        && right[i + size - 1][j] >= size && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

}
