package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @date 2023/4/12-13:38
 */
public class Problem_1147_LongestChunkedPalindromeDecomposition {

    public int longestDecomposition(String s) {
        int ans = 0;
        while (!s.isBlank()) {
            int i = 1, n = s.length();
            while (i <= n / 2 && !s.substring(0, i).equals(s.substring(n - i))) {
                i++;
            }

            if (i > n / 2) {
                ans++;
                break;
            }

            ans += 2;
            s = s.substring(i, n - i);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcd";
        String sub = s.substring(0, 1);
        System.out.println(s);
        System.out.println(sub);
    }
}
