package com.leetcode.problem_0801_1000;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ycb
 * @date 2022/5/21-11:34
 */
public class Problem_0961_NRepeatedElementInSize2nArray {

    public static int repeatedNTimes1(int[] nums) {
        int n = nums.length >> 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = -1;
        for (int key : map.keySet()) {
            if (map.get(key) == n) {
                ans = key;
                break;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int repeatedNTimes2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (i - 1 >= 0 && nums[i - 1] == cur) return cur;
            if (i - 2 >= 0 && nums[i - 2] == cur) return cur;
            if (i - 3 >= 0 && nums[i - 3] == cur) return cur;
        }
        return -1;
    }

    /*
    ====================================================================================================================
     */

    public static int repeatedNTimes3(int[] nums) {
        int n = nums.length;
        Random rank = new Random();
        int ans = -1;
        while (true) {
            int x = rank.nextInt(n);
            int y = rank.nextInt(n);
            if (x != y && nums[x] == nums[y]) {
                ans = nums[x];
                break;
            }
        }
        return ans;
    }
}
