package com.system.III_training.day38;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/25-10:58
 */
public class Problem_0448_FindAllNumbersDisappearedInAnArray {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            // 从i位置开始，玩下标循环怼
            walkNext(nums, i);
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public static void walkNext(int[] arr, int i) {
        while (arr[i] != i + 1) {
            int next = arr[i] - 1;
            if (arr[next] == next + 1) {
                break;
            }
            swap(arr, i, next);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
