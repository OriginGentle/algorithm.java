package com.leetcode.problem_0401_0600;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/6/1-15:56
 */
public class Problem_0473_MatchsticksToSquare {

    public static boolean makesquare1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : arr) sum += num;
        if (sum % 4 != 0) return false;
        Arrays.sort(arr);
        int[] edges = new int[4];
        return process(arr, 0, edges, sum / 4);
    }

    public static boolean process(int[] arr, int index, int[] edges, int len) {
        if (index == arr.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] += arr[index];
            if (edges[i] <= len && process(arr, index + 1, edges, len)) {
                return true;
            }
            edges[i] -= arr[index];
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    public static boolean makesquare2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : arr) sum += num;
        if (sum % 4 != 0) {
            return false;
        }
        int len = sum / 4;
        int n = arr.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            for (int k = 0; k < n; k++) {
                if ((i & (1 << k)) == 0) {
                    continue;
                }
                int cur = i & ~(1 << k);
                if (dp[cur] >= 0 && dp[cur] + arr[k] <= len) {
                    dp[i] = (dp[cur] + arr[k]) % len;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;
    }

    /*
    ====================================================================================================================
     */

    int[] ms;
    int t;

    public boolean makesquare3(int[] _ms) {
        ms = _ms;
        int sum = 0;
        for (int i : ms) sum += i;
        t = sum / 4;
        if (t * 4 != sum) return false;
        Arrays.sort(ms);
        return dfs(ms.length - 1, new int[4]);
    }

    boolean dfs(int idx, int[] cur) {
        if (idx == -1) return true;
        out:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                if (cur[j] == cur[i]) continue out;
            }
            int u = ms[idx];
            if (cur[i] + u > t) continue;
            cur[i] += u;
            if (dfs(idx - 1, cur)) return true;
            cur[i] -= u;
        }
        return false;
    }
}
