package com.nowcoder.HJ;

import java.util.Scanner;

/**
 * @author ycb
 * @since 2022/3/22-14:32
 */
public class HJ2_Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine().toLowerCase();
        String s2 = sc.nextLine().toLowerCase();
        int ans = KMP(s1, s2);
        System.out.println(ans);
        sc.close();
    }

    public static int KMP(String s, String match) {
        if (s == null || match == null || match.length() > s.length()) {
            return 0;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = match.toCharArray();
        int[] next = getNextArray(str2);
        int x = 0, y = 0, ans = 0;
        while (x < str1.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
                if (y == str2.length) {
                    ans++;
                    y = 0;
                    continue;
                }
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return ans;
    }


    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int n = match.length;
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int i = 2, cn = 0;
        while (i < n) {
            if (match[i - 1] == match[cn]) {
                next[i++] = +cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
