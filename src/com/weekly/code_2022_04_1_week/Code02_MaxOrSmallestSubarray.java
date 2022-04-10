package com.weekly.code_2022_04_1_week;

/**
 * @author ycb
 * @date 2022/4/9
 * @desc 找到非负数组中拥有"最大或的结果"的最短子数组
 */
public class Code02_MaxOrSmallestSubarray {

    public static int longest1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0;
        for (int num : arr) {
            max |= num;
        }
        if (max == 0) {
            return 1;
        }
        int n = arr.length;
        int ans = n;
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = i; j < n; j++) {
                cur |= arr[j];
                if (cur == max) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int longest2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = 0;
        for (int num : arr) {
            max |= num;
        }
        if (max == 0) {
            return 1;
        }
        int n = arr.length;
        int ans = n;
        int[] count = new int[32];
        int l = 0;
        int cur = 0;
        for (int r = 0; r < n; r++) {
            cur = add(count, arr[r]);
            while (cur == max) {
                cur = delete(count, arr[l++]);
            }
            // cur != max
            if (l > 0) {
                cur = add(count, arr[--l]);
            }
            if (cur == max) {
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans;
    }

    private static int delete(int[] count, int num) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            count[i] -= (num & (1 << i)) == 0 ? 0 : 1;
            ans |= (count[i] > 0 ? 1 : 0) << i;
        }
        return ans;
    }

    private static int add(int[] count, int num) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            count[i] += (num & (1 << i)) == 0 ? 0 : 1;
            ans |= (count[i] > 0 ? 1 : 0) << i;
        }
        return ans;
    }

    // for test
    public static int[] randomArray(int len, int val) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * val);
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 2000;
        int val = 500;
        int times = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            int size = (int) (Math.random() + 1) * len;
            int[] arr = randomArray(size, val);
            int ans1 = longest1(arr);
            int ans2 = longest2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
