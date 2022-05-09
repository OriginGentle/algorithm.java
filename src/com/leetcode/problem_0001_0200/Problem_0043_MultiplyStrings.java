package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2021/12/1-13:25
 */
public class Problem_0043_MultiplyStrings {

    public static String multiply1(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int N = num1.length(), M = num2.length();
        int[] res = new int[N + M];
        for (int i = N - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = M - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) {
                continue;
            }
            result.append(res[i]);
        }
        return result.toString();
    }

    /*
    ====================================================================================================================
     */

    public static String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s1 = "10889";
        String s2 = "762";
        String ans1 = multiply2(s1, s2);
        String ans2 = String.valueOf(Integer.parseInt(s1) * Integer.parseInt(s2));
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
