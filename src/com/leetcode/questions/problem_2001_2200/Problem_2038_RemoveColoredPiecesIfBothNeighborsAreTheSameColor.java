package com.leetcode.questions.problem_2001_2200;

/**
 * @author ycb
 * @since 2022/3/22-15:18
 */
public class Problem_2038_RemoveColoredPiecesIfBothNeighborsAreTheSameColor {

    public boolean winnerOfGame(String colors) {
        char[] str = colors.toCharArray();
        int ar = 0, br = 0;
        for (int i = 1; i < str.length - 1; i++) {
            if (str[i] == 'A' && str[i - 1] == 'A' && str[i + 1] == 'A') ar++;
            if (str[i] == 'B' && str[i - 1] == 'B' && str[i + 1] == 'B') br++;
        }
        return ar > br;
    }
}
