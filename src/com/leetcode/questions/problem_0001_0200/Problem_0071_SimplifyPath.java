package com.leetcode.questions.problem_0001_0200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @since 2022/1/6-19:20
 */
public class Problem_0071_SimplifyPath {

    public static String simplifyPath(String path) {
        String[] str = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String s : str) {
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (s.length() > 0 && !".".equals(s)) {
                stack.offerLast(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            sb.append("/");
        } else {
            while (!stack.isEmpty()) {
                sb.append("/").append(stack.pollFirst());
            }
        }
        return sb.toString();
    }
}
