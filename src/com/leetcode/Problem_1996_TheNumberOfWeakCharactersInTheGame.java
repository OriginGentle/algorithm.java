package com.leetcode;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2022/1/28-8:37
 */
public class Problem_1996_TheNumberOfWeakCharactersInTheGame {

    public static int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int ans = 0, maxDef = 0;
        for (int[] p : properties) {
            if (p[1] < maxDef) {
                ans++;
            } else {
                maxDef = p[1];
            }
        }
        return ans;
    }
}
