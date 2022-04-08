package com.leetcode;

/**
 * @author ycb
 * @date 2022/4/7
 */
public class Problem_0796_RotateString {

    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
