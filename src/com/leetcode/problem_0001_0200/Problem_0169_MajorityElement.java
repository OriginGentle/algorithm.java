package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/7/12-16:19
 */
public class Problem_0169_MajorityElement {

    public static int majorityElement(int[] nums) {
        int cand = 0;
        int Hp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (Hp == 0) {
                cand = nums[i];
                Hp++;
            } else if (nums[i] == cand) {
                Hp++;
            } else {
                Hp--;
            }
        }
        return cand;
    }
}
