package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/5/8-14:24
 */
public class Problem_0442_FindAllDuplicatesInAnArray {

    public List<Integer> findDuplicates1(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
            i++;
        }
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (nums[j] - 1 != j) {
                res.add(nums[j]);
            }
        }
        return res;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*
    ====================================================================================================================
     */

    public List<Integer> findDuplicates2(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = Math.abs(nums[i]);
            if (nums[cur - 1] > 0) {
                nums[cur - 1] = -nums[cur - 1];
            } else {
                res.add(cur);
            }
        }
        return res;
    }
}
