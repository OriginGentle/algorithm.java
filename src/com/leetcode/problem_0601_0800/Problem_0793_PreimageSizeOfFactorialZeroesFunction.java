package com.leetcode.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/8/28-15:29
 */
public class Problem_0793_PreimageSizeOfFactorialZeroesFunction {

    public static int preimageSizeFZF(int k) {
        if (k <= 1)
            return 5;

        return func(k) - func(k - 1);
    }

    public static int func(int x) {
        long l = 0, r = (long) 1e10;
        while (l <= r) {
            long m = (l + r) >> 1;
            if (getCnt(m) <= x) {
                l = m + 1;
            } else
                r = m - 1;
        }
        return (int) r + 1;
    }

    public static long getCnt(long num) {
        long ans = 0;
        while (num != 0) {
            ans += num / 5;
            num /= 5;
        }
        return ans;
    }

    public static void main(String[] args) {
        int k = 5;
        int ans = preimageSizeFZF(k);
        System.out.println(ans);
    }
}
