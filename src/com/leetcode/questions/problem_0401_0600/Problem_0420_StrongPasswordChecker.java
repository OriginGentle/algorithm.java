package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @date 2023/4/7-11:52
 */
public class Problem_0420_StrongPasswordChecker {

    public static int strongPasswordChecker(String password) {
        char[] p = password.toCharArray();
        int n = password.length();
        int lower = 0, upper = 0, digit = 0;

        for (char c : p) {
            if (c >= '0' && c <= '9') {
                digit = 1;
            } else if (c >= 'a' && c <= 'z') {
                lower = 1;
            } else if (c >= 'A' && c <= 'Z') {
                upper = 1;
            }
        }

        int kinds = lower + upper + digit;
        if (n < 6) { // 只需要 增加or替换
            return Math.max(6 - n, 3 - kinds);

        } else if (n <= 20) { // 本质上只需要 增加or替换
            int total = 0;
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && p[j] == p[i]) {
                    j++;
                }

                int cnt = j - i;
                if (cnt >= 3) {
                    total += cnt / 3;
                }

                i = j;
            }
            return Math.max(total, 3 - kinds);

        } else {
            int total = 0;
            int[] cnts = new int[3];
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && p[j] == p[i]) {
                    j++;
                }

                int cnt = j - i;
                if (cnt >= 3) {
                    total += cnt / 3;
                    cnts[cnt % 3]++;
                }
                i = j;
            }

            int rest = n - 20, cur = rest;
            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    cnts[i] = total;
                }

                if (cnts[i] != 0 && cur != 0) {
                    int t = Math.min(cnts[i] * (i + 1), cur);
                    cur -= t;
                    total -= t / (i + 1);
                }
            }
            return rest + Math.max(total, 3 - kinds);
        }
    }
}
