package com.system.D_weekly.code_2023_03_4_week;

/**
 * @author ycb
 * @date 2023/3/23-10:04
 * @desc 一共有n个项目，每个项目都有两个信息
 * projects[i] = {a, b}
 * 表示i号项目做完要a天，但是当你投入b个资源，它就会缩短1天的时间
 * 你一共有k个资源，你的目标是完成所有的项目，但是希望总天数尽可能缩短
 * 在所有项目同时开工的情况下，返回尽可能少的天数
 * 1 <= n <= 10^5
 * 1 <= k <= 10^7
 */
public class Code01_MinDaysDoneAllProjects {

    public static int minDays(int[][] projects, int k) {
        int l = 0, r = 0;
        for (int[] p : projects) {
            r = Math.max(p[0], r);
        }

        int m, ans = r;
        while (l <= r) {
            m = (l + r) / 2;
            if (yeah(projects, m) <= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 在 days 天内完成所有的项目
    // 需要多少资源
    private static int yeah(int[][] projects, int days) {
        int ans = 0;
        for (int[] p : projects) {
            if (p[0] > days) {
                ans += (p[0] - days) * p[1];
            }
        }
        return ans;
    }
}
