package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/9/8-08:32
 */
public class Problem_0667_BeautifulArrangementII {

    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int idx = 0;
        int l = 1, r = n;

        for (int i = 0; i < k; i++) {
            ans[idx++] = (i & 1) == 0 ? l++ : r--;
        }

        while (idx < n)
            ans[idx++] = (k & 1) == 0 ? r-- : l++;
        return ans;
    }
}
