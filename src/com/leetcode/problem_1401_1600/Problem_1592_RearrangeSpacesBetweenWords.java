package com.leetcode.problem_1401_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/9/7-09:21
 */
public class Problem_1592_RearrangeSpacesBetweenWords {

    public static String reorderSpaces(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int sp = 0, n = s.length();
        StringBuilder sb = new StringBuilder();
        List<String> cnt = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                sp++;
                if (sb.length() > 0) {
                    cnt.add(sb.toString());
                    sb.setLength(0);
                }
            } else
                sb.append(s.charAt(i));

        }

        if (sb.length() > 0) {
            cnt.add(sb.toString());
            sb.setLength(0);
        }

        int len = cnt.size() - 1;
        for (int i = 0; i < len; i++) {
            sb.append(cnt.get(i));
            sb.append(" ".repeat(sp / len));
        }

        sb.append(cnt.get(len));
        sb.append(" ".repeat(len == 0 ? sp : sp % len));
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "str    word  hello";
        reorderSpaces(s);
    }
}
