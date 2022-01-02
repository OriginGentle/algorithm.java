package com.weekly.code_2021_12_5_week;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2021/12/30-9:52
 * @description https://leetcode.com/problems/loud-and-rich/
 */
public class Code01_LoudAndRich {

    // richer[i] = {a, b} a比b更有钱  a -> b
    // quiet[i] = k, i这个人安静值是k
    // 利用拓扑排序
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int N = quiet.length;
        // a -> b
        // a -> c
        // a : b c
        ArrayList<ArrayList<Integer>> nexts = new ArrayList();
        for (int i = 0; i < N; i++) {
            nexts.add(new ArrayList<>());
        }
        // 入度表
        int[] degree = new int[N];
        for (int[] r : richer) {
            nexts.get(r[0]).add(r[1]);
            degree[r[1]]++;
        }
        int[] zeroIn = new int[N];
        int l = 0, r = 0;
        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) {
                zeroIn[r++] = i;
            }
        }
        // ans[i] = j -> 比i更有钱的人里面，最安静的是j
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = i;
        }
        while (l < r) { // 如果队列不为空
            // 弹出一个入度为0的点
            int cur = zeroIn[l++];
            for (int next : nexts.get(cur)) {
                if (quiet[ans[next]] > quiet[ans[cur]]) {
                    ans[next] = ans[cur];
                }
                if (--degree[next] == 0) {
                    zeroIn[r++] = next;
                }
            }
        }
        return ans;
    }
}
