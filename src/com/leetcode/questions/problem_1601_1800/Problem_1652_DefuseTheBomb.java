package com.leetcode.questions.problem_1601_1800;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/9/24-12:42
 */
public class Problem_1652_DefuseTheBomb {

    public int[] decrypt(int[] code, int k) {
        if (k == 0) {
            Arrays.fill(code, 0);
            return code;
        }
        int n = code.length;
        int[] ans = new int[n];
        int[] sum = new int[n * 2 + 10];

        for (int i = 1; i <= 2 * n; i++) {
            sum[i] += sum[i - 1] + code[(i - 1) % n];
        }

        for (int i = 1; i <= n; i++) {
            if (k < 0)
                ans[i - 1] = sum[i + n - 1] - sum[i + n + k - 1];
            else
                ans[i - 1] = sum[i + k] - sum[i];
        }
        return ans;
    }
}
