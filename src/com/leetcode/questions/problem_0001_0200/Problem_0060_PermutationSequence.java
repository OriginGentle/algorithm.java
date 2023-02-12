package com.leetcode.questions.problem_0001_0200;

import java.util.Arrays;

/**
 * @author ycb
 * @since 2021/12/15-8:30
 */
public class Problem_0060_PermutationSequence {

    public static String getPermutation(int n, int k) {
        // 准备阶乘数组
        int[] help = new int[n];
        help[0] = 1;
        for (int i = 1; i < n; i++) {
            help[i] = help[i - 1] * i;
        }
        StringBuilder sb = new StringBuilder();
        --k;
        // 0...n
        // 0位置弃而不用
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; i++) {
            // 确定第一位是哪个数字
            int sort = k / help[n - i] + 1;
            for (int j = 1; j <= n; j++) {
                sort -= valid[j];
                if (sort == 0) {
                    sb.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= help[n - i];
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 16;
        String ans = getPermutation(n, k);
        System.out.println(ans);
    }
}
