package com.system.IV_weekly.code_2022_04_1_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/4/9
 * @desc 小红书第二题：
 * 薯队长最近在参加了一个活动，主办方提供了N个礼物以供挑选，
 * 每个礼物有一个价值，范围在0 ~ 10^9之间，
 * 薯队长可以从中挑选k个礼物
 * 返回：其中价值最接近的两件礼物之间相差值尽可能大的结果
 * <p>
 * 小红书第三题：
 * 薯队长最近在玩一个游戏，这个游戏桌上会有一排不同颜色的方块，
 * 每次薯队长可以选择一个方块，便可以消除这个方块以及和他左右相临的
 * 若干的颜色相同的方块，而每次消除的方块越多，得分越高。
 * 具体来说，桌上有以个方块排成一排 (1 <= N <= 200），
 * 每个方块有一个颜色，用1~N之间的一个整数表示，相同的数宇代表相同的颜色，
 * 每次消除的时候，会把连续的K个相同颜色的方块消除，并得到K*K的分数，
 * 直到所有方块都消除。显然，不同的消除顺序得分不同，薯队长希望您能告诉他，这个游戏最多能得到多少分
 * base目录下，代码包：day46，消箱子原题，RemoveBoxes
 */
public class Code05_PickKNumbersNearTowNumberMaxDiff {

    public static int maxNear(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int l = 0;
        int r = arr[n - 1] - arr[0];
        int m = 0;
        int ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (can(arr, k, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    // 返回arr中能否挑出k件价值差至少为m的礼品
    private static boolean can(int[] arr, int k, int limit) {
        int last = arr[0];
        int pick = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - last >= limit) {
                pick++;
                last = arr[i];
            }
        }
        return pick >= k;
    }
}
