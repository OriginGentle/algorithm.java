package com.leetcode.questions.problem_0401_0600;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @date 2022/5/2-17:01
 */
public class Problem_0591_TagValidator {

    static final String CDATA_START = "<![CDATA[";
    static final String CDATA_END = "]]>";

    public static boolean isValid(String code) {
        Deque<String> d = new ArrayDeque<>();
        int n = code.length();
        int i = 0;
        while (i < n) {
            if (i + 8 < n && code.substring(i, i + 9).equals(CDATA_START)) {
                if (i == 0) return false;
                int j = i + 9;
                boolean can = false;
                while (j < n && !can) {
                    if (j + 2 < n && code.substring(j, j + 3).equals(CDATA_END)) {
                        j = j + 3;
                        can = true;
                    } else {
                        j++;
                    }
                }
                if (!can) return false;
                i = j;
            } else if (code.charAt(i) == '<') {
                if (i == n - 1) return false;
                boolean isEnd = code.charAt(i + 1) == '/';
                int p = isEnd ? i + 2 : i + 1;
                int j = p;
                while (j < n && code.charAt(j) != '>') {
                    if (!Character.isUpperCase(code.charAt(j))) return false;
                    j++;
                }
                if (j == n) return false;
                int len = j - p;
                if (len < 1 || len > 9) return false;
                String tag = code.substring(p, j);
                i = j + 1;
                if (!isEnd) {
                    d.addLast(tag);
                } else {
                    if (d.isEmpty() || !d.pollLast().equals(tag)) return false;
                    if (d.isEmpty() && i < n) return false;
                }
            } else {
                if (i == 0) return false;
                i++;
            }
        }
        return d.isEmpty();
    }

    public static void main(String[] args) {
        String code = "<DIV>This is the first line <![CDATA[<div>]]></DIV>";
        boolean ans = isValid(code);
        System.out.println(ans);
    }
}
