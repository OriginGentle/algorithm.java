package com.leetcode.questions.problem_1001_1200;

import java.util.Stack;

/**
 * @author ycb
 * @date 2022/5/28-13:14
 */
public class Problem_1021_RemoveOutermostParentheses {

    public static String removeOuterParentheses1(String s) {
        StringBuffer sb = new StringBuffer();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ')') {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                sb.append(cur);
            }
            if (cur == '(') {
                stack.push(cur);
            }
        }
        return sb.toString();
    }

    public String removeOuterParentheses2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, score = 0; i < n; ) {
            int j = i;
            while (i == j || score != 0) score += cs[j++] == '(' ? 1 : -1;
            sb.append(s, i + 1, j - 1);
            i = j;
            score = 0;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "(()())(())";
        String ans = removeOuterParentheses1(s);
        System.out.println(ans);
    }
}
