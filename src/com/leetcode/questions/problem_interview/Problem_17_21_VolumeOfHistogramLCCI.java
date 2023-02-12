package com.leetcode.questions.problem_interview;

/**
 * @author ycb
 * @date 2023/2/2-11:26
 */
public class Problem_17_21_VolumeOfHistogramLCCI {

    public static int trap(int[] height) {
        int ans = 0;
        int L = 0, R = height.length - 1;
        int lMax = 0, rightMax = 0;
        while (L < R) {
            lMax = Math.max(lMax, height[L]);
            rightMax = Math.max(rightMax, height[R]);
            if (height[L] < height[R]) {
                ans += lMax - height[L];
                L++;
            } else {
                ans += rightMax - height[R];
                --R;
            }
        }
        return ans;
    }
}
