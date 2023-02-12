package com.system.C_training.day24;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2021/10/9-8:23
 * @description 长度为N的数组arr，一定可以组成N^2个数字对
 * 例如arr = [3,1,2]，数字对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)
 * 也就是任意两个数都可以，而且自己和自己也算数字对
 * 数字对怎么排序？
 * 第一维数据从小到大；第一维数据一样的，第二维数组也从小到大
 * 所以上面的数值对排序的结果为：(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)
 * 给定一个数组arr，和整数k，返回第k小的数值对
 */
public class Code02_KthMinPair {

    public static class Pair {
        public int x;
        public int y;

        public Pair(int a, int b) {
            x = a;
            y = b;
        }
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
        }
    }

    // 时间复杂度：O(N^2 * log (N^2))
    public static int[] kthMinPair1(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        Pair[] pairs = new Pair[N * N];
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pairs[index++] = new Pair(arr[i], arr[j]);
            }
        }
        Arrays.sort(pairs, new PairComparator());
        return new int[]{pairs[k - 1].x, pairs[k - 1].y};
    }

    /*
    ====================================================================================================================
     */

    // 时间复杂度：O(N * logN)
    public static int[] kthMinPair2(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        // O(N * logN)
        Arrays.sort(arr);
        int firstNum = arr[(k - 1) / N];
        int lessFirstNumSize = 0;
        int firstNumSize = 0;
        for (int i = 0; i < N && arr[i] <= firstNum; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            } else {
                firstNumSize++;
            }
        }
        int rest = k - lessFirstNumSize * N;
        return new int[]{firstNum, arr[(rest - 1) / firstNumSize]};
    }

    /*
    ====================================================================================================================
     */

    public static int[] kthMinPair3(int[] arr, int k) {
        int N = arr.length;
        if (k > N * N) {
            return null;
        }
        // 在无序数组中，找到第K小的数，返回值
        // 第K小，以1作为开始
        int firstNum = getMinKth(arr, (k - 1) / N);
        // 第1维数字
        int lessFirstNumSize = 0;
        int firstNumSize = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] < firstNum) {
                lessFirstNumSize++;
            }
            if (arr[i] == firstNum) {
                firstNumSize++;
            }
        }
        int rest = k - (lessFirstNumSize * N);
        return new int[]{firstNum, getMinKth(arr, (rest - 1) / firstNumSize)};
    }

    // 改写快排，时间复杂度O(N)
    // 在无序数组arr中，找到，如果排序的话，arr[index]的数是什么？
    public static int getMinKth(int[] arr, int index) {
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L];
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] getRandomArray(int max, int len) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int max = 100;
        int len = 30;
        int testTimes = 100000;
        System.out.println("test begin, test times : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            int[] arr = getRandomArray(max, len);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int N = arr.length * arr.length;
            int k = (int) (Math.random() * N) + 1;
            int[] ans1 = kthMinPair1(arr1, k);
            int[] ans2 = kthMinPair2(arr2, k);
            int[] ans3 = kthMinPair3(arr3, k);
            if (ans1[0] != ans2[0] || ans2[0] != ans3[0] || ans1[1] != ans2[1] || ans2[1] != ans3[1]) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
