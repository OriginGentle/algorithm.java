package com.leetcode.questions.problem_1801_2000;

/**
 * @author ycb
 * @date 2024/7/7-02:07
 */
public class Problem_1958_CheckIfMoveIsLegal {

    public static boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int k = 0; k < 8; k++) {

            if (check(board, rMove, cMove, color, dx[k], dy[k])) {
                return true;
            }

        }

        return false;
    }

    // 判断每个方向是否存在以操作位置为起点的好线段
    public static boolean check(char[][] board, int rMove, int cMove, char color, int dx, int dy) {
        int x    = rMove + dx;
        int y    = cMove + dy;
        int step = 1;   // 当前遍历到的节点序号
        while (x >= 0 && x < 8 && y >= 0 && y < 8) {
            if (step == 1) {
                // 第一个点必须为相反颜色
                if (board[x][y] == '.' || board[x][y] == color) {
                    return false;
                }
            } else {
                // 好线段中不应存在空格子
                if (board[x][y] == '.') {
                    return false;
                }
                // 遍历到好线段的终点，返回 true
                if (board[x][y] == color) {
                    return true;
                }
            }

            step++;
            x += dx;
            y += dy;
        }
        // 不存在符合要求的好线段
        return false;
    }
}
