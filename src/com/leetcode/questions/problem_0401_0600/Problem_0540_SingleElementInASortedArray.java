package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @since 2022/2/14-16:38
 */
public class Problem_0540_SingleElementInASortedArray {

    // 时间复杂度:O(N) 空间复杂度:O(1)
    public int singleNonDuplicate1(int[] nums) {
        int target = 0;
        for (int num : nums) {
            target ^= num;
        }
        return target;
    }

    /*
    ====================================================================================================================
     */

    public int singleNonDuplicate2(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (m % 2 == 0) { // 偶数
                if (m + 1 < n && nums[m] == nums[m + 1]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            } else { // 奇数
                if (m - 1 >= 0 && nums[m] == nums[m - 1]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
        }
        return nums[r];
    }
}
