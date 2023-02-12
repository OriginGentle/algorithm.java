package com.system.D_weekly.code_2022_01_4_week;

/**
 * @author ycb
 * @date 2022/2/10-17:40
 * @description 来自米哈游
 * https://leetcode.com/problems/battleships-in-a-board/
 */
public class Code04_BattleshipsInABoard {

    public static int countBattleships(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X'
                        && (i == 0 || board[i - 1][j] != 'X')
                        && (j == 0 || board[i][j - 1] != 'X')) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
