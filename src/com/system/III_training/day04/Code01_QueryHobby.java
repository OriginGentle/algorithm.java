package com.system.III_training.day04;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author ycb
 * @Date 2021/8/1-17:25
 * @Description 字节原题
 * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)
 * 意思是在数组里下标0~3这个范围上，有几个2？答案返回2。
 * 假设给你一个数组arr，
 * 对这个数组的查询非常频繁，都给出来
 * 请返回所有查询的结果
 */
public class Code01_QueryHobby {

    // 暴力解
    public static class QueryBox1 {

        private int[] arr;

        public QueryBox1(int[] Array) {
            arr = new int[Array.length];
            for (int i = 0; i < Array.length; i++) {
                arr[i] = Array[i];
            }
        }

        public int query(int L, int R, int V) {
            int ans = 0;
            for (; L <= R; L++) {
                if (arr[L] == V) {
                    ans++;
                }
            }
            return ans;
        }
    }

    /*
    ====================================================================================================================
     */

    public static class QueryBox2 {

        private HashMap<Integer, ArrayList<Integer>> map;

        // 用map记录arr中每个数出现的位置
        public QueryBox2(int[] arr) {
            map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (!map.containsKey(arr[i])) {
                    map.put(arr[i], new ArrayList<>());
                }
                map.get(arr[i]).add(i);
            }
        }

        public int query(int L, int R, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            ArrayList<Integer> indexMap = map.get(value);
            // 查询 < L 的下标有几个
            int a = countLess(indexMap, L);
            // 查询 < R+1 的下标有几个
            int b = countLess(indexMap, R + 1);
            return b - a;
        }

        // 用二分法，找到 < limit 的最右位置
        private int countLess(ArrayList<Integer> arr, int limit) {
            int L = 0;
            int R = arr.size() - 1;
            int mostR = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (arr.get(mid) < limit) {
                    mostR = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return mostR + 1;
        }
    }

    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 300;
        int value = 20;
        int testTimes = 10000;
        int queryTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(len, value);
            int N = arr.length;
            QueryBox1 box1 = new QueryBox1(arr);
            QueryBox2 box2 = new QueryBox2(arr);
            for (int j = 0; j < queryTimes; j++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                int L = Math.min(a, b);
                int R = Math.max(a, b);
                int v = (int) (Math.random() * value) + 1;
                if (box1.query(L, R, v) != box2.query(L, R, v)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");
    }

}
