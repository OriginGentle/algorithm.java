package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @since 2022/2/25-8:50
 */
public class Problem_0537_ComplexNumberMultiplication {

    public static String complexNumberMultiply(String num1, String num2) {
        String[] s1 = num1.split("\\+|i"), s2 = num2.split("\\+|i");
        int a = Integer.parseInt(s1[0]), b = Integer.parseInt(s1[1]);
        int c = Integer.parseInt(s2[0]), d = Integer.parseInt(s2[1]);
        int lAns = a * c - b * d, rAns = b * c + a * d;
        return lAns + "+" + rAns + "i";
    }
}
