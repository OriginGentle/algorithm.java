package com.system.IV_weekly.code_2022_06_2_week;

/**
 * @author ycb
 * @date 2022/6/19-14:51
 * @desc https://leetcode.com/problems/number-of-different-subsequences-gcds/
 */
public class Code03_NumberOfDifferentSubsequencesGCDs {

    // n : 数组中的最大值
    // n/1 + n/2 + n/3 + ... + n/n --> O(N * logN)
    public static int countDifferentSubsequenceGCDs(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        boolean[] cntSet = new boolean[max + 1];
        for (int num : nums) {
            cntSet[num] = true;
        }
        int ans = 0;
        for (int a = 1; a <= max; a++) {
            int m = a;
            for (; m <= max; m += a) {
                if (cntSet[m]) break;
            }
            for (int n = m; n <= max; n += a) {
                if (cntSet[n]) {
                    m = gcd(m, n);
                    if (m == a) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
