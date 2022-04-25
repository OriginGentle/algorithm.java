package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ycb
 * @since 2022/4/25-14:53
 */
public class Problem_0398_RandomPickIndex {

    public static class Solution1 {

        public Random random = new Random();
        public Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        public Solution1(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                ArrayList<Integer> posList = map.getOrDefault(nums[i], new ArrayList<>());
                posList.add(i);
                map.put(nums[i], posList);
            }
        }

        public int pick(int target) {
            ArrayList<Integer> posList = map.get(target);
            return posList.get(random.nextInt(posList.size()));
        }
    }

    /*
    ====================================================================================================================
     */

    public static class Solution2 {
        Random random = new Random();
        int[] nums;

        public Solution2(int[] _nums) {
            nums = _nums;
        }

        public int pick(int target) {
            int n = nums.length;
            int ans = 0;
            for (int i = 0, cnt = 0; i < n; i++) {
                if (nums[i] == target) {
                    cnt++;
                    if (random.nextInt(cnt) == 0) {
                        ans = i;
                    }
                }
            }
            return ans;
        }
    }
}
