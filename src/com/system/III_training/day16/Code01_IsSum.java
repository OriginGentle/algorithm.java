package com.system.III_training.day16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ycb
 * @date 2021/9/12-11:13
 * @description 给定一个有正、有负、有0的数组arr，给定一个整数k，
 * 返回arr的子集是否能累加出k
 * 1）正常怎么做？
 * 2）如果arr中的数值很大，但是arr的长度不大，怎么做？
 */
public class Code01_IsSum {

    // 暴力解
    public static boolean isSum1(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        return process1(arr, arr.length - 1, sum);
    }

    // 可以自由使用arr[0...i]上的数字，能不能累加得到sum
    public static boolean process1(int[] arr, int i, int rest) {
        if (rest == 0) {
            return true;
        }
        if (i == -1) {
            return false;
        }
        return process1(arr, i - 1, rest) || process1(arr, i - 1, rest - arr[i]);
    }

    /*
    ====================================================================================================================
     */

    // 记忆化搜索
    public static boolean isSum2(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        return process2(arr, arr.length - 1, sum, new HashMap<>());
    }

    public static boolean process2(int[] arr, int i, int sum, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
        if (dp.containsKey(i) && dp.get(i).containsKey(sum)) {
            return dp.get(i).get(sum);
        }
        boolean ans = false;
        if (sum == 0) {
            ans = true;
        } else if (i != -1) {
            ans = process2(arr, i - 1, sum, dp) || process2(arr, i - 1, sum - arr[i], dp);
        }
        if (!dp.containsKey(i)) {
            dp.put(i, new HashMap<>());
        }
        dp.get(i).put(sum, ans);
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 动态规划
    public static boolean isSum3(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        // sum != 0
        if (arr == null || arr.length == 0) {
            return false;
        }
        int max = 0;
        int min = 0;
        for (int num : arr) {
            max += num > 0 ? num : 0;
            min += num < 0 ? num : 0;
        }
        if (sum > max || sum < min) {
            return false;
        }
        // min <= sum <= max
        int N = arr.length;
        // dp[i][j] : 在arr[0...i]范围上能否凑出累积和为j的子集
        boolean[][] dp = new boolean[N][max - min + 1];
        dp[0][-min] = true;
        dp[0][arr[0] - min] = true;
        for (int i = 1; i < N; i++) {
            for (int j = min; j <= max; j++) {
                dp[i][j - min] = dp[i - 1][j - min];
                int next = j - min - arr[i];
                dp[i][j - min] |= (next >= 0 && next <= max - min && dp[i - 1][next]);
            }
        }
        return dp[N - 1][sum - min];
    }

    /*
    ====================================================================================================================
     */

    // 第二问:分治
    public static boolean isSum4(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        if (arr.length == 1) {
            return arr[0] == sum;
        }
        int N = arr.length;
        int mid = N >> 1;
        HashSet<Integer> leftSum = new HashSet<>();
        HashSet<Integer> rightSum = new HashSet<>();
        process4(arr, 0, mid, 0, leftSum);
        process4(arr, mid, N, 0, rightSum);
        for (int l : leftSum) {
            if (rightSum.contains(sum - l)) {
                return true;
            }
        }
        return false;
    }

    // arr[0...i-1]决定已经做完了！形成的累加和是pre
    // arr[i...end - 1] end(终止)  所有数字随意选择，
    // arr[0...end-1]所有可能的累加和存到ans里去
    public static void process4(int[] arr, int i, int end, int pre, HashSet<Integer> ans) {
        if (i == end) {
            ans.add(pre);
        } else {
            process4(arr, i + 1, end, pre, ans);
            process4(arr, i + 1, end, pre + arr[i], ans);
        }
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * ((max << 1) + 1)) - max;
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 20;
        int M = 100;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * (N + 1));
            int[] arr = randomArray(size, M);
            int sum = (int) (Math.random() * ((M << 1) + 1)) - M;
            boolean ans1 = isSum1(arr, sum);
            boolean ans2 = isSum2(arr, sum);
            boolean ans3 = isSum3(arr, sum);
            boolean ans4 = isSum4(arr, sum);
            if (ans1 ^ ans2 || ans3 ^ ans4 || ans1 ^ ans3) {
                System.out.println("出错了！");
                System.out.print("arr : ");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("sum : " + sum);
                System.out.println("方法一答案 : " + ans1);
                System.out.println("方法二答案 : " + ans2);
                System.out.println("方法三答案 : " + ans3);
                System.out.println("方法四答案 : " + ans4);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
