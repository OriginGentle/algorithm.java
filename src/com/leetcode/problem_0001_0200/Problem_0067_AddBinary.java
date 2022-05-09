package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2022/1/7-13:11
 */
public class Problem_0067_AddBinary {

    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        for (; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            carry = sum / 2;
        }
        ans.append(carry == 1 ? carry : "");
        return ans.reverse().toString();
    }

    /*
    ====================================================================================================================
     */

    public static void main(String[] args) {
        String a = "11000100111";
        String b = "1001100";
        String ans = addBinary(a, b);
        System.out.println(ans);
    }
}
