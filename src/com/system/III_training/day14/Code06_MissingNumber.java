package com.system.III_training.day14;

/**
 * @author ycb
 * @date 2021/9/8-9:12
 * @description https://leetcode.com/problems/first-missing-positive/
 */
public class Code06_MissingNumber {

    public static int firstMissingPositive(int[] arr) {
        // 有效区 : L位置上按照顺序一次放着 L + 1的数
        int L = 0;
        // 无效区
        int R = arr.length;
        while (L != R) {
            if (arr[L] == L + 1) {
                L++;
            } else if (arr[L] <= L || arr[L] > R || arr[L] == arr[arr[L] - 1]) {
                swap(arr, L, --R);
            } else {
                swap(arr, L, arr[L] - 1);
            }
        }
        return L + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
