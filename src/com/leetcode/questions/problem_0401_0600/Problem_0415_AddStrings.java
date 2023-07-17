package com.leetcode.questions.problem_0401_0600;

/**
 * @author ycb
 * @date 2023/7/17-23:51
 */
public class Problem_0415_AddStrings {

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuffer sb = new StringBuffer();
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int curSum = x + y + carry;
            sb.append(curSum % 10);
            carry = curSum / 10;
            i--;
            j--;
        }
        sb.reverse();
        return sb.toString();
    }
}
