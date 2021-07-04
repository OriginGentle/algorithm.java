package com.leetcode;

import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/7/4-15:40
 */
public class Code645_FindErrorNums {
    public static int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int N = nums.length;
        // key:value -> arr[i]:count
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        for (int i = 1; i <= N; i++) {
            if (map.get(i) == null) {
                ans[1] = i;
            } else if (map.get(i) == 2) {
                ans[0] = i;
            }
        }
        return ans;
    }

    public static int[] findErrorNums1(int[] nums) {
        int[] ans = new int[2];
        int N = nums.length;
        int[] arr = new int[N + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
        }
        for (int i = 1; i <= arr.length; i++) {
            if (arr[i] == 0){
                ans[1] = i;
            }
            if (arr[i] == 2){
                ans[0] = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 4};
        int[] ans = findErrorNums(arr);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
