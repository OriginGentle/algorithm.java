package com.leetcode.problem_2001_2200;

/**
 * @author ycb
 * @since 2022/3/15-8:13
 */
public class Problem_2044_CountNumberOfMaximumBitwiseOrSubsets {

    public int max = 0, ans = 0;
    public int[] help;

    public int countMaxOrSubsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        help = nums;
        process(0, 0);
        return ans;
    }

    public void process(int index, int val) {
        if (index == help.length) {
            if (val > max) {
                max = val;
                ans = 1;
            } else if (val == max) {
                ans++;
            }
            return;
        }
        // 不要当前位置的数
        process(index + 1, val);
        process(index + 1, val | help[index]);
    }
}
