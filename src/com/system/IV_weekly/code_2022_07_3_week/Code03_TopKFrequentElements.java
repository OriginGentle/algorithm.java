package com.system.IV_weekly.code_2022_07_3_week;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/7/23-13:18
 * @desc https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class Code03_TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> cmt = new HashMap<>();
        for (int num : nums) {
            cmt.put(num, cmt.getOrDefault(num, 0) + 1);
        }
        int i = cmt.size();
        int[][] arr = new int[i][2];
        for (Map.Entry<Integer, Integer> entry : cmt.entrySet()) {
            arr[--i][0] = entry.getKey();
            arr[i][1] = entry.getValue();
        }
        moreLess(arr, 0, arr.length - 1, k);
        int[] ans = new int[k];
        for (; i < k; i++) {
            ans[i] = arr[i][0];
        }
        return ans;
    }

    private static void moreLess(int[][] arr, int l, int r, int k) {
        if (k == r - l + 1) {
            return;
        }
        swap(arr, r, l + (int) (Math.random() * (r - l + 1)));
        int pivot = partition(arr, l, r);
        if (pivot - l == k) {
            return;
        } else if (pivot - l > k) {
            moreLess(arr, l, pivot - 1, k);
        } else {
            moreLess(arr, pivot, r, k - pivot + l);
        }
    }

    private static int partition(int[][] arr, int l, int r) {
        int left = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index][1] <= arr[r][1]) {
                index++;
            } else {
                swap(arr, ++left, index++);
            }
        }
        swap(arr, ++left, r);
        return left;
    }

    private static void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
