package com.leetcode.questions.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/6/3-16:53
 */
public class Problem_0829_ConsecutiveNumbersSum {

    // 假设存在某段连续之和为n，首项为a，项数为k
    // 根据等差公式：(a + a + k - 1) * k = 2n
    // 简单变形：(2a + k - 1) * k = 2n  --> 2a = (2n / k) - k + 1
    // 首项a和k均为正整数 2a >= 2  --> (2n / k) - k + 1 >= 2
    // 进一步： (2n / k) >= k + 1
    // 所以： (2n / k) > k
    // 综上，根据(2a + k − 1) × k = 2n 和 (2n / k) > k
    // k 必然是 2n 的约数,并且为较小的约数
    public static int consecutiveNumbersSum(int n) {
        int ans = 0;
        n <<= 1;
        for (int k = 1; k * k < n; k++) {
            if (n % k != 0) continue;
            if ((n / k - (k - 1)) % 2 == 0) ans++;
        }
        return ans;
    }
}
