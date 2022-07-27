package com.leetcode.problem_0401_0600;

/**
 * @author ycb
 * @date 2022/7/27-15:41
 */
public class Problem_0592_FractionAdditionAndSubtraction {

    public static String fractionAddition(String exp) {
        char[] str = exp.toCharArray();
        int n = str.length;
        String ans = "";
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && str[j] != '+' && str[j] != '-') {
                j++;
            }
            String num = exp.substring(i, j);
            if (ans.equals(""))
                ans = num;
            else
                ans = clc(num, ans);
            i = j;
        }
        return ans;
    }

    public static String clc(String a, String b) {
        long[] as = pares(a);
        long[] bs = pares(b);
        long p = as[0] * bs[1];
        long q = bs[0] * as[1];

        long r = q + p;
        long m = as[1] * bs[1];
        long gcd = gcd(Math.abs(r), m);
        return (r / gcd) + "/" + (m / gcd);
    }

    public static long[] pares(String s) {
        int n = s.length();
        int index = 1;
        while (index < n && s.charAt(index) != '/') {
            index++;
        }
        long a = Long.parseLong(s.substring(0, index));
        long b = Long.parseLong(s.substring(index + 1));
        return new long[]{a, b};
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        String exp = "1/3-1/2";
        String ans = fractionAddition(exp);
        System.out.println(ans);
    }
}
