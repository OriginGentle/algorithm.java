package com.system.C_training.day23;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author ycb
 * @date 2021/10/8-8:20
 * @description 定义什么是可整合数组：
 * 一个数组排完序之后，除了最左侧的数外，有arr[i] = arr[i-1]+1
 * 则称这个数组为可整合数组
 * 比如{5,1,2,4,3}、{6,2,3,1,5,4}都是可整合数组
 * 返回arr中最长可整合子数组的长度
 */
public class Code03_LongestIntegratedLength {

    public static int getLIL1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        // O(N^3 * log N)
        for (int start = 0; start < arr.length; start++) { // 子数组所有可能的开头
            for (int end = start; end < arr.length; end++) {  // 开头为start的情况下，所有可能的结尾
                if (isIntegrated(arr, start, end)) {
                    len = Math.max(len, end - start + 1);
                }
            }
        }
        return len;
    }

    public static boolean isIntegrated(int[] arr, int left, int right) {
        int[] newArr = Arrays.copyOfRange(arr, left, right + 1);
        // O(N * logN)
        Arrays.sort(newArr);
        for (int i = 1; i < newArr.length; i++) {
            if (newArr[i] != newArr[i - 1] + 1) {
                return false;
            }
        }
        return true;
    }

    /*
    ====================================================================================================================
     */

    public static int getLIL2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int ans = 1;
        HashSet<Integer> set = new HashSet<>();
        for (int L = 0; L < N; L++) {
            set.clear();
            int min = arr[L];
            int max = arr[L];
            set.add(arr[L]);
            // L..R
            for (int R = L + 1; R < N; R++) {
                if (set.contains(arr[R])) {
                    break;
                }
                set.add(arr[R]);
                min = Math.min(min, arr[R]);
                max = Math.max(max, arr[R]);
                if (max - min == R - L) { // 可整合数组:max - min = 个数 - 1
                    ans = Math.max(ans, R - L + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 5, 3, 2, 6, 4, 3};
        System.out.println(getLIL1(arr));
        System.out.println(getLIL2(arr));
    }
}
