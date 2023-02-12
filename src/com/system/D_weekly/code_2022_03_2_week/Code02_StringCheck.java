package com.system.D_weekly.code_2022_03_2_week;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/3/12-13:36
 * @description 来自字节飞书团队
 * 小歪每次会给你两个字符串：
 * 笔记s1和关键词s2，请你写一个函数
 * 判断s2的排列之一是否是s1的子串
 * 如果是，返回true
 * 否则，返回false
 */
public class Code02_StringCheck {

    public static boolean check1(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        char[] str2 = s2.toCharArray();
        Arrays.sort(str2);
        s2 = String.valueOf(str2);
        for (int L = 0; L < s1.length(); L++) {
            for (int R = L; R < s1.length(); R++) {
                char[] cur = s1.substring(L, R + 1).toCharArray();
                Arrays.sort(cur);
                String curSort = String.valueOf(cur);
                if (curSort.equals(s2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    // 词频统计 + 窗口
    public static boolean check2(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        char[] str2 = s2.toCharArray();
        char[] count = new char[256];
        for (int i = 0; i < str2.length; i++) {
            count[str2[i]]++;
        }
        int M = str2.length;
        char[] str1 = s1.toCharArray();
        int inInvalidTimes = 0;
        int R = 0;
        for (; R < M; R++) {
            if (count[str1[R]]-- <= 0) {
                inInvalidTimes++;
            }
        }
        for (; R < str1.length; R++) {
            if (inInvalidTimes == 0) {
                return true;
            }
            if (count[str1[R]]-- <= 0) {
                inInvalidTimes++;
            }
            if (count[str1[R - M]]++ < 0) {
                inInvalidTimes--;
            }
        }
        return inInvalidTimes == 0;
    }
}
