package com.leetcode.questions.problem_0001_0200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ycb
 * @since 2021/11/17-9:36
 */
public class Problem_0018_4Sum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        int n = nums.length;
        // 确定第一个数
        for (int i = 0; i < n - 3; i++) {
            // 相同的情况
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) {
                continue;
            }
            // 确定第二个数
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, -1000000000, -1000000000, -1000000000, -1000000000};
        int target = -1000000000;
        List<List<Integer>> ans = fourSum(arr, target);
        ans.forEach(System.out::print);
    }
}
