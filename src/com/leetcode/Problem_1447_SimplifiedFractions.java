package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/2/10-18:05
 */
public class Problem_1447_SimplifiedFractions {

    // 0 <= n <= 100
    // 时间复杂度：n^2
    public static List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 1) {
            return ans;
        }
        // 分母不能为0 或者 1
        // 结果必须得在0 和 1之间，
        // 分母必须得比分子大
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) {
                    ans.add(i + "/" + j);
                }
            }
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
