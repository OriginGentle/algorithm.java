package com.system.D_weekly.code_2023_07_4_week;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2023/8/6-11:57
 * @desc 自 01背包问世之后，小 A 对此深感兴趣
 * 一天，小 A 去远游，却发现他的背包不同于 01 背包，他的物品大致可分为 k 组
 * 每组中的物品只能选择1件，现在他想知道最大的利用价值是多少
 * 测试链接 : www.luogu.com.cn/problem/P1757
 */
public class Code02_TeamDP {

    public static int MAXN = 1001;

    public static int MAXM = 1001;

    public static int[][] arr = new int[MAXN][3];

    public static int[] dp = new int[MAXM];

    public static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // 背包容量
            m = (int) in.nval;
            in.nextToken();
            // 物品数量
            n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
                in.nextToken();
                arr[i][2] = (int) in.nval;
            }

            Arrays.sort(arr, 0, n, Comparator.comparingInt(a -> a[2]));
            Arrays.fill(dp, 0, m + 1, 0);
            out.println(compute());
            out.flush();
        }
    }

    public static int compute() {
        for (int start = 0, end = 1; start < n; ) {

            while (end < n && arr[end][2] == arr[start][2]) {
                end++;
            }

            for (int r = m; r >= 0; r--) {
                for (int i = start; i < end; i++) {
                    if (r >= arr[i][0]) {
                        dp[r] = Math.max(dp[r], dp[r - arr[i][0]] + arr[i][1]);
                    }
                }
            }

            start = end++;
        }
        return -1;
    }
}
