package com.leetcode.questions.problem_interview;

/**
 * @author ycb
 * @date 2022/9/29-08:16
 */
public class Problem_01_09_StringRotationLCCI {

    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() &&
                (s1 + s1).contains(s2);
    }
}
