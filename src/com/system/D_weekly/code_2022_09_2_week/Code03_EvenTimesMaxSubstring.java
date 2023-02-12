package com.system.D_weekly.code_2022_09_2_week;

import java.util.HashMap;

/**
 * @author ycb
 * @date 2022/9/15-10:25
 * @desc 来自微软
 * 给定一个字符串s，其中都是英文小写字母
 * 如果s中的子串含有的每种字符都是偶数个
 * 那么这样的子串就是达标子串，子串要求是连续串
 * 返回s中达标子串的最大长度
 * 1 <= s的长度 <= 10^5
 * 字符种类都是英文小写
 */
public class Code03_EvenTimesMaxSubstring {

    public static int maxLen1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                if (ok(s, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean ok(String s, int l, int r) {
        if (((r - l + 1) & 1) == 1)
            return false;

        int[] cmt = new int[26];
        for (int i = l; i <= r; i++) {
            cmt[s.charAt(i) - 'a']++;
        }

        for (int cu : cmt) {
            if ((cu & 1) == 1)
                return false;
        }
        return true;
    }

    /*
    ====================================================================================================================
     */

    public static int maxLen2(String s) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int status = 0, ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            status ^= 1 << (s.charAt(i) - 'a');
            if (map.containsKey(status))
                ans = Math.max(ans, i - map.get(status));

            else
                map.put(status, i);
        }
        return ans;
    }

    // for test
    public static String randomString(int n, int v) {
        char[] s = new char[n];
        for (int i = 0; i < n; i++) {
            s[i] = (char) ((int) (Math.random() * v) + 'a');
        }
        return String.valueOf(s);
    }

    public static void main(String[] args) {
        int n = 50;
        int v = 6;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String s = randomString(n, v);
            int ans1 = maxLen1(s);
            int ans2 = maxLen2(s);
            if (ans1 != ans2) {
                System.out.println(s);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
