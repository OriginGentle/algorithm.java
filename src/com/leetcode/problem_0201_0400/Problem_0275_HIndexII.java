package com.leetcode.problem_0201_0400;

/**
 * @author ycb
 * @date 2022/9/22-14:08
 */
public class Problem_0275_HIndexII {

    public int hIndex(int[] citations) {
        int n = citations.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (citations[m] >= n - m)
                r = m - 1;
            else
                l = m + 1;
        }
        return n - l;
    }
}
