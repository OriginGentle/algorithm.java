package com.training.day32;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2021/10/19-8:21
 * @description 给定一个数组arr，arr[i] = j，表示第i号试题的难度为j。给定一个非负数M
 * 想出一张卷子，对于任何相邻的两道题目，前一题的难度不能超过后一题的难度+M
 * 返回所有可能的卷子种数
 */
public class Code01_SequenceM {

    public static int ways1(int[] arr, int M) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, M);
    }

    public static int process(int[] arr, int index, int M) {
        if (index == arr.length) {
            for (int i = 1; i < index; i++) {
                if (arr[i - 1] > arr[i] + M) {
                    return 0;
                }
            }
            return 1;
        }
        int ans = 0;
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            ans += process(arr, index + 1, M);
            swap(arr, index, i);
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /*
    ====================================================================================================================
     */

    // 时间复杂度:O(N * logN)
    // 从左往右的动态规划 + 范围上二分
    public static int ways2(int[] arr, int M) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int all = 1;
        for (int i = 1; i < arr.length; i++) {
            all = all * (num(arr, i - 1, arr[i] - M) + 1);
        }
        return all;
    }

    // arr[0..r]上返回>=t的数有几个, 二分的方法
    // 找到 >=t 最左的位置a, 然后返回r - a + 1就是个数
    public static int num(int[] arr, int r, int t) {
        int i = 0;
        int j = r;
        int m = 0;
        int a = r + 1;
        while (i <= j) {
            m = (i + j) / 2;
            if (arr[m] >= t) {
                a = m;
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return r - a + 1;
    }

    /*
    ====================================================================================================================
     */

    // 时间复杂度:O(N * logV)
    // 从左往右的动态规划 + IndexTree
    public static int ways3(int[] arr, int M) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
            min = Math.min(min, num);
        }
        IndexTree iTree = new IndexTree(max - min + 2);
        Arrays.sort(arr);
        int a = 0, b = 0,all = 1;
        iTree.add(arr[0] - min + 1, 1);
        for (int i = 1; i < arr.length; i++) {
            a = arr[i] - min + 1;
            b = i - (a - M - 1 >= 1 ? iTree.sum(a - M - 1) : 0);
            all = all * (b + 1);
            iTree.add(a, 1);
        }
        return all;
    }

    public static class IndexTree {
        public int N;
        public int[] tree;

        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
        }

        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (value + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 20;
        int testTimes = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(len, value);
            int m = (int) (Math.random() * (value + 1));
            int ans1 = ways1(arr, m);
            int ans2 = ways2(arr, m);
            int ans3 = ways3(arr, m);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("出错了!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
