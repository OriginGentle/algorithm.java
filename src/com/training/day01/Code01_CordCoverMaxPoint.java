package com.training.day01;

import java.util.Arrays;

/**
 * @Author ycb
 * @Date 2021/7/13-8:32
 * @Description 给定一个有序数组arr，代表坐落在X轴上的点
 * 给定一个正数K，代表绳子的长度
 * 返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 */
public class Code01_CordCoverMaxPoint {

    // 贪心:数组中的每个点都是绳子覆盖的右边缘
    // 二分:数组有序，找离某个数最近的位置
    // 时间复杂度:N*logN
    public static int maxPoint1(int[] arr, int K) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - K);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    // 在arr中，以R为终止位置，往左找小于等于value，离value的数，返回其位置
    // 数组有序
    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    /*
    ====================================================================================================================
     */

    // 窗口
    public static int maxPoint2(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    /*
    ====================================================================================================================
     */

    // 暴力方法
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }


    public static int[] generateRandom(int maxSize, int maxValue) {
        int[] arr = new int[(int) Math.random() * (maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 1000;
        int maxValue = 100;
        int times = 100000;
        System.out.println("测试开始:");
        for (int i = 0; i < times; i++) {
            int L = (int) (Math.random() * maxValue);
            int[] arr = generateRandom(maxSize, maxValue);
            int ans1 = test(arr, L);
            int ans2 = maxPoint1(arr, L);
            int ans3 = maxPoint2(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops");
                System.out.print("ans1:" + ans1 + " ans2:" + ans2 + " ans3:" + ans3);
                break;
            }
        }
    }

}
