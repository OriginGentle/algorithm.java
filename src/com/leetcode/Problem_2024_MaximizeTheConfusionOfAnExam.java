package com.leetcode;

/**
 * @author ycb
 * @date 2022/3/29
 */
public class Problem_2024_MaximizeTheConfusionOfAnExam {

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(maxAns(answerKey, k, 'T'), maxAns(answerKey, k, 'F'));
    }

    private static int maxAns(String s, int k, char c) {
        int n = s.length();
        int ans = 0;
        int sum = 0;
        for (int L = 0, R = 0; R < n; R++) {
            sum += s.charAt(R) == c ? 0 : 1;
            while (sum > k) {
                sum -= s.charAt(L++) != c ? 1 : 0;
            }
            ans = Math.max(ans, R - L + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "TTFFTTFT";
        int ans = maxConsecutiveAnswers(str, 2);
        System.out.println(ans);
    }
}
