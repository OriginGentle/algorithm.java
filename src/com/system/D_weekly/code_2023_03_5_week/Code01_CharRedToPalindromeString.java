package com.system.D_weekly.code_2023_03_5_week;

/**
 * @author ycb
 * @date 2023/4/1-14:51
 * @desc 来自百度
 * 用r、e、d三种字符，拼出一个回文子串数量等于x的字符串
 * 1 <= x <= 10^5
 */
public class Code01_CharRedToPalindromeString {

    public static String palindromeX(int x) {
        StringBuilder sb = new StringBuilder();
        char cur = 'r';
        while (x > 0) {
            int num = near(x);
            for (int i = 0; i < num; i++) {
                sb.append(cur);
            }
            x -= num * (num + 1) / 2;
            cur = cur == 'r' ? 'e' : (cur == 'e' ? 'd' : 'r');
        }
        return sb.toString();
    }

    public static int near(int x) {
        int l = 1, r = x, ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            if (ok(m, x)) {
                l = m + 1;
                ans = m;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static boolean ok(int n, int x) {
        return ((long) n * (n + 1) / 2) <= x;
    }

    // for test
    public static void main(String[] args) {
        int x = 1000;
        System.out.println(palindromeX(x));
    }
}
