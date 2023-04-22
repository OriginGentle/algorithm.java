package com.system.D_weekly.code_2023_04_3_week;

/**
 * @author ycb
 * @date 2023/4/22-11:59
 * @desc 完美走位问题
 * 给定一个由'W'、'A'、'S'、'D'四种字符组成的字符串，长度一定是4的倍数
 * 你可以把任意连续的一段子串，变成'W'、'A'、'S'、'D'组成的随意状态
 * 目的是让4种字符词频一样
 * 返回需要修改的最短子串长度
 * 测试链接 : https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
 */
public class Code01_ReplaceTheSubstringForBalancedString {

    public static int balancedString(String str) {
        int n = str.length();
        int[] idxs = new int[n];
        int[] cnts = new int[4];
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            idxs[i] = c == 'Q' ? 0 : (c == 'W' ? 1 : (c == 'E' ? 2 : 0));
            cnts[idxs[i]]++;
        }

        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; l < n; l++) {
            while (!ok(cnts, l, r) && r < n) {
                cnts[idxs[r++]]--;
            }

            if (ok(cnts, l, r)) {
                ans = Math.min(ans, r - l);
            } else {
                break;
            }
            cnts[idxs[l]]++;
        }
        return ans;
    }


    // 窗口[l ... r)内的数随意变化，窗口外的数不能变化
    // 窗口的长度 r - l
    public static boolean ok(int[] cnts, int l, int r) {
        int maxCnt = Math.max(Math.max(cnts[0], cnts[1]), Math.max(cnts[2], cnts[3]));
        int needChanges = maxCnt * 4 - (cnts[0] + cnts[1] + cnts[2] + cnts[3]);
        int rest = r - l - needChanges;
        return rest >= 0 && rest % 4 == 0;
    }
}
