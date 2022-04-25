package com.leetcode;

/**
 * @author ycb
 * @since 2022/4/25-15:19
 */
public class Problem_0868_BinaryGap {

    public static int binaryGap(int n) {
        int ans = 0;
        for (int i = 31, j = -1; i >= 0; i--) {
            if (((n >> i) & 1) == 1) {
                if (j != -1) ans = Math.max(ans, j - i);
                j = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 22;
        int ans = binaryGap(n);
        System.out.println(ans);
    }
}
