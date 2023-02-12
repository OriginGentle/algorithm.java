package com.leetcode.questions.problem_0401_0600;

import java.util.*;

/**
 * @author ycb
 * @date 2022/6/16-08:32
 */
public class Problem_0532_KDiffPairsInAnArray {

    public static int findPairs1(int[] nums, int k) {
        if (nums == null || nums.length < 2 || k < 0) {
            return 0;
        }
        Map<Integer, ArrayList<Integer>> idxMap = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (!idxMap.containsKey(nums[i])) {
                idxMap.put(nums[i], new ArrayList<>());
            }
            idxMap.get(nums[i]).add(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            if (idxMap.containsKey(cur - k)) {
                ArrayList<Integer> idxInfo = idxMap.get(cur - k);
                ans += findTarget(idxInfo, i);
            }
            if (idxMap.containsKey(cur + k)) {
                ArrayList<Integer> idxInfo = idxMap.get(cur + k);
                ans += findTarget(idxInfo, i);
            }
        }
        return ans;
    }

    // idxList 位置信息，有序
    // 二分找 > i 的位置数量
    public static int findTarget(ArrayList<Integer> idxList, int i) {
        int l = 0;
        int r = idxList.size() - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) >> 1;
            if (idxList.get(m) <= i) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }
        return ans == -1 ? 0 : idxList.size() - ans;
    }

    /*
    ====================================================================================================================
     */

    public static int findPairs2(int[] nums, int k) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num + k)) {
                res.add(num);
            }
            if (visited.contains(num - k)) {
                res.add(num - k);
            }
            visited.add(num);
        }
        return res.size();
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 3, 1, 4, 1, 5};
        int k = 2;
        int ans1 = findPairs1(arr, k);
        int ans2 = findPairs2(arr, k);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
