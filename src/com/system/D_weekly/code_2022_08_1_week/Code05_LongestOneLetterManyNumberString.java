package com.system.D_weekly.code_2022_08_1_week;

/**
 * @author ycb
 * @date 2022/8/5-11:11
 * @desc 给定一个只由小写字母和数字字符组成的字符串str
 * 要求子串必须只含有一个小写字母，数字字符数量随意
 * 求这样的子串最大长度是多少
 */
public class Code05_LongestOneLetterManyNumberString {

    // 暴力解
    public static int right(String s) {
        char[] str = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            for (int j = i; j < str.length; j++) {
                if (check(str, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    public static boolean check(char[] str, int l, int r) {
        int letterNumber = 0;
        for (int i = l; i <= r; i++) {
            if (str[i] >= 'a' && str[i] <= 'z') {
                letterNumber++;
            }
        }
        return letterNumber == 1;
    }

    /*
    ====================================================================================================================
     */

    // 窗口
    public static int maxLen(String s) {
        char[] str = s.toCharArray();
        // lc：窗口内的字母数量
        // [left,right)
        int lc = 0, right = 0, n = str.length;
        int ans = 0;
        for (int left = 0; left < n; left++) {
            while (right < n) {
                if (lc == 1 && str[right] >= 'a' && str[right] <= 'z') {
                    break;
                }
                if (str[right] >= 'a' && str[right] <= 'z') {
                    lc++;
                }
                right++;
            }
            if (lc == 1) {
                ans = Math.max(ans, right - left);
            }
            if (str[left] >= 'a' && str[left] <= 'z') {
                lc--;
            }
        }
        return ans;
    }

    // for test
    public static char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    public static String randomString(int n) {
        char[] str = new char[n];
        for (int i = 0; i < n; i++) {
            str[i] = chars[(int) (Math.random() * chars.length)];
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int N = 100;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            String str = randomString(n);
            int ans1 = right(str);
            int ans2 = maxLen(str);
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
