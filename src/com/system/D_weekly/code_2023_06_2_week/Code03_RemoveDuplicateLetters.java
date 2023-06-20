package com.system.D_weekly.code_2023_06_2_week;

/**
 * @author ycb
 * @date 2023/6/19-08:58
 * @desc 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次
 * 需保证 返回结果的字典序最小
 * 要求不能打乱其他字符的相对位置)
 * 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/
 */
public class Code03_RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {
        int[] cnts = new int[26];
        boolean[] enters = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            cnts[s.charAt(i) - 'a']++;
        }

        char[] stack = new char[26];
        int size = 0;

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (!enters[cur - 'a']) {
                // 单调栈
                // 大压小
                while (size > 0 &&
                        stack[size - 1] > cur &&
                        cnts[stack[size - 1] - 'a'] > 0) {
                    enters[stack[size - 1] - 'a'] = false;
                    size--;
                }

                stack[size++] = cur;
                enters[cur - 'a'] = true;
            }

            cnts[cur - 'a']--;
        }

        return String.valueOf(stack, 0, size);
    }
}
