package leetcode;

import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/3/11-17:45
 * <p>
 * 给定一个整数数组nums和一个整数目标值 target，请你在该数组中找出和为目标值的两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 */
public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] index = new int[2];
            HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums.length; i++) {
                if (hash.containsKey(nums[i])) {
                    index[0] = i;
                    index[1] = hash.get(nums[i]);
                    return index;
                }
                hash.put(target - nums[i], i);
            }
            return index;
        }
    }
}
