package com.system.III_training.day34;

/**
 * @author ycb
 * @since 2021/10/22-9:22
 */
public class Problem_0287_FindTheDuplicateNumber {

    // 快慢指针
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = nums[0];
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
