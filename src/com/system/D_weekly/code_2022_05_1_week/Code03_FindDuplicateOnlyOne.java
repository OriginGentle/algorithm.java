package com.system.D_weekly.code_2022_05_1_week;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ycb
 * @date 2022/5/8-15:01
 * @desc 大厂面试题
 * 1、2、3...n-1、n、n、n+1、n+2...
 * 在这个序列中，只有一个数字有重复(n)
 * 这个序列是无序的，找到重复数字n
 * 这个序列是有序的，找到重复数字n
 */
public class Code03_FindDuplicateOnlyOne {

    // 暴力方法
    public static int right(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }

    /*
    ====================================================================================================================
     */

    // 符合题目要求的、有序数组，找重复数
    // 时间复杂度O(logN)，额外空间复杂度O(1)
    public static int findDuplicateSorted(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if ((m - 1 >= 0 && arr[m] == arr[m - 1]) || (m + 1 <= arr.length - 1 && arr[m] == arr[m + 1])) {
                ans = arr[m];
                break;
            }
            if (m - l == arr[m] - arr[l]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 符合题目要求的、无序数组，找重复数
    // 时间复杂度O(N)，额外空间复杂度O(1)
    public static int findDuplicate(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int fast = arr[arr[0]];
        int slow = arr[0];
        while (fast != slow) {
            fast = arr[arr[fast]];
            slow = arr[slow];
        }
        fast = 0;
        while (fast != slow) {
            fast = arr[fast];
            slow = arr[slow];
        }
        return slow;
    }

    // for test
    public static int[] randomArray(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ans[i] = i + 1;
        }
        ans[n] = (int) (Math.random() * n) + 1;
        for (int i = n; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int tmp = ans[i];
            ans[i] = ans[j];
            ans[j] = tmp;
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int testTime = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray((int) (Math.random() * N) + 1);
            if (right(arr) != findDuplicate(arr)) {
                System.out.println("未排序情况出错!");
                break;
            }
            Arrays.sort(arr);
            if (right(arr) != findDuplicateSorted(arr)) {
                System.out.println("排序情况出错!");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(right(arr));
                System.out.println(findDuplicateSorted(arr));
                break;
            }
        }
        System.out.println("测试结束");
    }
}
