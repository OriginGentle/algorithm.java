package com.leetcode.problem_0201_0400;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/9/22-11:05
 */
public class Problem_0274_HIndex {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = citations.length - 1, h = 0;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }
}
