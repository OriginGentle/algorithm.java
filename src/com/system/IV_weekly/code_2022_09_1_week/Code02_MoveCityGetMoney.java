package com.system.IV_weekly.code_2022_09_1_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/9/14-13:52
 * @desc 来自美团
 * 有n个城市，城市从0到n-1进行编号。小美最初住在k号城市中
 * 在接下来的m天里，小美每天会收到一个任务
 * 她可以选择完成当天的任务或者放弃该任务
 * 第i天的任务需要在ci号城市完成，如果她选择完成这个任务
 * 若任务开始前她恰好在ci号城市，则会获得ai的收益
 * 若她不在ci号城市，她会前往ci号城市，获得bi的收益
 * 当天的任务她都会当天完成
 * 任务完成后，她会留在该任务所在的ci号城市直到接受下一个任务
 * 如果她选择放弃任务，她会停留原地，且不会获得收益
 * 小美想知道，如果她合理地完成任务，最大能获得多少收益
 * 输入描述: 第一行三个正整数n, m和k，表示城市数量，总天数，初始所在城市
 * 第二行为m个整数c1, c2,...... cm，其中ci表示第i天的任务所在地点为ci
 * 第三行为m个整数a1, a2,...... am，其中ai表示完成第i天任务且地点不变的收益
 * 第四行为m个整数b1, b2,...... bm，其中bi表示完成第i天的任务且地点改变的收益
 * 0 <= k, ci <= n <= 30000
 * 1 <= m <= 30000
 * 0 <= ai, bi <= 10^9
 * 输出描述 输出一个整数，表示小美合理完成任务能得到的最大收益
 */
public class Code02_MoveCityGetMoney {

    public static int maxProfit1(int n, int m, int k, int[] c, int[] a, int[] b) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        return process(k, 0, m, c, a, b, dp);
    }

    public static int process(int cur, int i, int m, int[] c, int[] a, int[] b, int[][] dp) {
        if (i == m)
            return 0;

        if (dp[cur][i] != -1)
            return dp[cur][i];

        int p1 = process(cur, i + 1, m, c, a, b, dp);

        int p2 = 0;

        if (cur == c[i])
            p2 = process(c[i], i + 1, m, c, a, b, dp) + a[i];

        else
            p2 = process(c[i], i + 1, m, c, a, b, dp) + b[i];

        int ans = Math.max(p1, p2);
        dp[cur][i] = ans;
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int maxProfit2(int n, int m, int k, int[] c, int[] a, int[] b) {
        SegmentTree seg = new SegmentTree(n);
        seg.update(k, 0);

        int ans = 0;
        for (int i = 0; i < m; i++) {

            int curAns = Math.max(
                    Math.max(seg.max(0, c[i] - 1), seg.max(c[i] + 1, n - 1)) + b[i],
                    seg.max(c[i], c[i]) + a[i]);

            ans = Math.max(curAns, ans);
            seg.update(c[i], curAns);
        }
        return ans;
    }

    public static class SegmentTree {
        private final int n;
        private final int[] max;

        public SegmentTree(int N) {
            n = N;
            max = new int[(n + 1) << 2];
            Arrays.fill(max, Integer.MIN_VALUE);
        }

        public void update(int index, int C) {
            index++;
            update(index, index, C, 1, n, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = C;
                return;
            }
            int m = (l + r) >> 1;
            if (L <= m)
                update(L, R, C, l, m, rt << 1);

            if (R > m)
                update(L, R, C, m + 1, r, rt << 1 | 1);

            pushUp(rt);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        public int max(int l, int r) {
            l++;
            r++;
            if (l > r)
                return Integer.MIN_VALUE;

            return max(l, r, 1, n, 1);
        }

        private int max(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }

            int m = (l + r) >> 1;

            int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;

            if (L <= m)
                left = max(L, R, l, m, rt << 1);

            if (R > m)
                right = max(L, R, m + 1, r, rt << 1 | 1);

            return Math.max(left, right);
        }
    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 100;
        int M = 100;
        int V = 10000;
        int testTimes = 5000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int m = (int) (Math.random() * M) + 1;
            int k = (int) (Math.random() * n);
            int[] c = randomArray(m, n);
            int[] a = randomArray(m, V);
            int[] b = randomArray(m, V);
            int ans1 = maxProfit1(n, m, k, c, a, b);
            int ans2 = maxProfit2(n, m, k, c, a, b);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 100000;
        int m = 100000;
        int v = 1000000;
        int k = (int) (Math.random() * n);
        int[] c = randomArray(m, n);
        int[] a = randomArray(m, v);
        int[] b = randomArray(m, v);
        System.out.println("城市数量 : " + n);
        System.out.println("任务天数 : " + m);
        System.out.println("收益数值规模 : " + v);
        long start = System.currentTimeMillis();
        maxProfit2(n, m, k, c, a, b);
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + "毫秒");
        System.out.println("性能测试结束");
    }
}
