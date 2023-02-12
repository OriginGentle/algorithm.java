package com.system.C_training.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @date 2021/8/13-8:17
 * @description 数组中所有数都异或起来的结果，叫做异或和
 * 给定一个数组arr，可以任意切分成若干个不相交的子数组
 * 其中一定存在一种最优方案，使得切出异或和为0的子数组最多
 * 返回这个最多数量
 */
public class Code04_MostXorZero {

    // 暴力解 O(2^N)
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // 准备一个前缀异或和数组
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process(eor, 1, new ArrayList<>());
    }

    // index去决定:前一坨部分，结不结束！
    // 如果结束！就把index放入到parts里去
    // 如果不结束，就不放
    // parts代表切分位置，比如parts:{1},代表[0,1):左闭右开,被切成一块的是[0~0]
    public static int process(int[] eor, int index, List<Integer> parts) {
        int ans = 0;
        if (index == eor.length) {
            parts.add(eor.length);
            ans = eorZeroParts(eor, parts);
            // 恢复现场
            parts.remove(parts.size() - 1);
        } else {
            int p1 = process(eor, index + 1, parts);
            parts.add(index);
            int p2 = process(eor, index + 1, parts);
            parts.remove(parts.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int eorZeroParts(int[] eor, List<Integer> parts) {
        int L = 0;
        int ans = 0;
        for (Integer end : parts) {
            if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
                ans++;
            }
            L = end;
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    // 假设可能性法 O(N) --> 假设客观上的最优状况，去分析这个状况拥有的特征、性质，设计coding流程，不要让这个状况错过即可
    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // dp[i] = arr[0...i]中能切出最多异或和为0的部分
        int[] dp = new int[N];

        // key 某一个前缀异或和
        // value 这个前缀异或和上次出现的位置(最晚！)
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        // 0~i整体的异或和
        int xor = 0;
        // 根据描述，一定存在最优解的划分方案:假设当前是最优划分方案
        // 只关注最后一个划分部分
        // 可能性1：最后一个部分异或和不是0 --> dp[i] = dp[i-1]
        // 可能性2：最后一个部分异或和是0 --> dp[j] + 1  j:某一个前缀和为N出现的最晚位置
        // 假设最后一个部分的范围是j~i
        // 假设j~i范围上的一个任意位置为k,则j ~ k的异或和不能为0,k+1 ~ i的异或和也不能为0
        // 那么：假设0~i的整体异或和为N,最后一个划分部分的起始位置就是某一个前缀和为N出现的最晚位置
        // 比如 0~5异或和100  0~23异或和100  0~27异或和100  0~39异或和100
        // 现在i在39位置,最后一个异或为0的部分就应该是28~29
        for (int i = 0; i < N; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) { // 可能性2
                int pre = map.get(xor);
                // pre == -1 --> 0...i只有这一块
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
        }
        return dp[N - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }


    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
