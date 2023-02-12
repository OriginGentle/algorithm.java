package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @date 2022/6/21-09:52
 */
public class Problem_1108_DefangingAnIpAddress {

    public String defangIPaddr(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length(), idx = -1;
        while (++idx < n) {
            char c = s.charAt(idx);
            if (c == '.') sb.append('[');
            sb.append(c);
            if (c == '.') sb.append(']');
        }
        return sb.toString();
    }
}
