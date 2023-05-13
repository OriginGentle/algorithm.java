package com.system.D_weekly.code_2023_05_2_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2023/5/13-16:19
 * @desc 一个图像有n个像素点，存储在一个长度为n的数组arr里，
 * 每个像素点的取值范围[0,s]的整数
 * 请你给图像每个像素点值加上一个整数k（可以是负数）
 * 像素值会自动截取到[0,s]范围，
 * 当像素值<0，会更改为0，当新像素值>s，会更改为s
 * 这样就可以得到新的arr，想让所有像素点的平均值最接近中位值s/2, 向下取整
 * 请输出这个整数k, 如有多个整数k都满足, 输出小的那个
 * 1 <= n <= 10^6
 * 1 <= s <= 10^18
 */
public class Code01_ImageArrayAverageGotoHalf {

    public static int best1(int[] arr, int s) {
        int half = s / 2;
        int average = Integer.MIN_VALUE;
        int ans = -s;
        // 枚举所有可能的 k 值
        for (int i = -s; i <= s; i++) {
            int curAverage = average1(arr, i, s);

            if (Math.abs(half - curAverage) < Math.abs(half - average)) {
                average = curAverage;
                ans = i;
            }
        }
        return ans;
    }

    public static int average1(int[] arr, int k, int s) {
        int sum = 0;
        for (int num : arr) {
            int val = num + k;
            if (val < 0) {
                sum += 0;
            } else {
                sum += Math.min(val, s);
            }
        }
        return sum / arr.length;
    }

    /*
    ====================================================================================================================
     */

    public static int best2(int[] arr, int s) {
        int l = -s, r = s;
        int half = s / 2, average = -s;
        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            int curAverage = average1(arr, m, s);

            if (Math.abs(half - curAverage) < Math.abs(half - average) ||
                    (Math.abs(half - curAverage) == Math.abs(half - average) && m < ans)) {

                ans = m;
                average = curAverage;
            }

            if (curAverage >= half) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int best3(int[] arr, int s) {
        Arrays.sort(arr);
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = arr[i] + preSum[i - 1];
        }

        int l = -s, r = s;
        int half = s / 2, average = -s;
        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            int curAverage = average3(arr, preSum, m, s);

            if (Math.abs(half - curAverage) < Math.abs(half - average) ||
                    (Math.abs(half - curAverage) == Math.abs(half - average) && m < ans)) {

                average = curAverage;
                ans = m;
            }

            if (curAverage >= half) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    private static int average3(int[] arr, int[] pre, int k, int s) {
        int n = arr.length;
        if (k < 0) {
            int l = bsZero(arr, k);
            int sum = rangeSum(pre, l + 1, n - 1);
            return (sum + (k * (n - l - 1))) / n;
        } else {
            int r = bsS(arr, k, s);
            int sum = rangeSum(pre, 0, r - 1);
            return (sum + (k * r) + (s * (n - r))) / n;
        }
    }

    public static int bsZero(int[] arr, int k) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] + k <= 0) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static int bsS(int[] arr, int k, int s) {
        int l = 0, r = arr.length - 1;
        int ans = arr.length;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] + k >= s) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static int rangeSum(int[] preSum, int l, int r) {
        if (l > r) {
            return 0;
        }

        return l == 0 ? preSum[r] : preSum[r] - preSum[l - 1];
    }

    // for test
    public static int[] randomArray(int n, int s) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (s + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 50;
        int S = 500;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int s = (int) (Math.random() * S) + 1;
            int[] arr = randomArray(n, s);
            int ans1 = best1(arr, s);
            int ans2 = best2(arr, s);
            int ans3 = best3(arr, s);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
