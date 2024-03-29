package com.system.D_weekly.code_2023_03_4_week;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ycb
 * @date 2023/3/23-10:05
 * @desc 你驾驶出租车行驶在一条有 n 个地点的路上
 * 这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n
 * 通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向
 * 乘客信息用一个下标从 0 开始的二维数组 rides 表示
 * 其中 rides[i] = [starti, endi, tipi]
 * 表示第 i 位乘客需要从地点 starti 前往 endi
 * 愿意支付 tipi 元的小费
 * 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元
 * 你同时 最多 只能接一个订单。
 * 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。
 * 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
 * https://leetcode.cn/problems/maximum-earnings-from-taxi/
 */
public class Code03_MaximumEarningsFromTaxi {

    private static final int MAXN = 100001;

    public static long[] max = new long[MAXN << 2];

    public static int n;

    public static void build(int l, int r, int rt) {
        if (l == r) {
            max[rt] = 0;
        } else {
            int mid = (l + r) / 2;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }
    }

    public static long max(int r) {
        if (r < 1) {
            return 0;
        }
        return max(1, r, 1, n, 1);
    }

    private static long max(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return max[rt];
        }
        int mid = (l + r) >> 1;
        long ans = 0;
        if (L <= mid) {
            ans = Math.max(ans, max(L, R, l, mid, rt << 1));
        }
        if (R > mid) {
            ans = Math.max(ans, max(L, R, mid + 1, r, rt << 1 | 1));
        }
        return ans;
    }

    public static void update(int index, long c) {
        update(index, c, 1, n, 1);
    }

    private static void update(int index, long c, int l, int r, int rt) {
        if (l == r) {
            max[rt] = Math.max(max[rt], c);
        } else {
            int mid = (l + r) >> 1;
            if (index <= mid) {
                update(index, c, l, mid, rt << 1);
            } else {
                update(index, c, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }
    }

    private static void pushUp(int rt) {
        max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
    }

    public static long maxTaxiEarnings1(int len, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));
        n = len;
        build(1, n, 1);
        for (int[] ride : rides) {
            long money = max(ride[0]) + ride[1] - ride[0] + ride[2];
            update(ride[1], money);
        }
        return max(n);
    }

    /*
    ====================================================================================================================
     */

    public static int[] sorted = new int[MAXN];

    public static long[] dp = new long[MAXN];

    public static long maxTaxiEarnings2(int len, int[][] rides) {
        int m = rides.length;
        for (int i = 0, j = 0; i < m; i++) {
            sorted[j++] = rides[i][0];
            sorted[j++] = rides[i][1];
        }

        Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(sorted, 0, m << 1);
        Arrays.fill(dp, 0, m << 1, 0);

        int dpi = 0;
        // pre : 之前的最大收入
        long pre = 0, ans = 0;
        for (int[] ride : rides) {
            int start = ride[0], end = ride[1], tips = ride[2];
            int sRank = rank(sorted, m << 1, start);
            int eRank = rank(sorted, m << 1, end);

            while (dpi <= sRank) {
                pre = Math.max(pre, dp[dpi++]);
            }

            long money = pre + end - start + tips;
            ans = Math.max(ans, money);
            dp[eRank] = Math.max(dp[eRank], money);
        }
        return ans;
    }

    public static int rank(int[] sorted, int len, int num) {
        int ans = 0, l = 0, r = len - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (sorted[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
