package com.leetcode.questions.problem_1401_1600;

/**
 * @author ycb
 * @since 2022/1/5-8:34
 */
public class Problem_1576_ReplaceAllSToAvoidConsecutiveRepeatingCharacters {

    public static String modifyString(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (str[i] == '?') {
                for (char ch = 'x'; ch <= 'z'; ch++) {
                    if ((i > 0 && str[i - 1] == ch) || (i < n - 1 && str[i + 1] == ch)) {
                        continue;
                    }
                    str[i] = ch;
                    break;
                }
            }
        }
        return String.valueOf(str);
    }
}
