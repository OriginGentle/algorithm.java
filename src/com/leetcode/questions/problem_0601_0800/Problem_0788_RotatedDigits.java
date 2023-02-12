package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/9/25-19:21
 */
public class Problem_0788_RotatedDigits {

    public int rotatedDigits(int n) {
        int ans = 0;
        out:
        for (int i = 1; i <= n; i++) {
            boolean ok = false;
            int x = i;
            while (x != 0) {
                int t = x % 10;
                x /= 10;
                if (t == 2 || t == 5 || t == 6 || t == 9)
                    ok = true;
                else if (t != 0 && t != 1 && t != 8)
                    continue out;
            }
            if (ok)
                ans++;
        }
        return ans;
    }
}
