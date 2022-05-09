package com.leetcode.problem_2001_2200;

/**
 * @author ycb
 * @since 2022/3/8-8:19
 */
public class Problem_2055_PlatesBetweenCandles {

    // '*' 表示盘子，'｜' 表示蜡烛
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int m = queries.length;
        int[] preSum = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        int[] ans = new int[m];
        int sum = 0;
        for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
            if (s.charAt(i) == '|') p = i;
            if (s.charAt(j) == '|') q = j;
            left[i] = p;
            right[j] = q;
            sum = sum + (s.charAt(i) == '*' ? 1 : 0);
            preSum[i] = sum;
        }
        for (int i = 0; i < m; i++) {
            // 起始位置往右第一跟蜡烛的位置
            int posR = right[queries[i][0]];
            // 结束位置往左第一根蜡烛的位置
            int posL = left[queries[i][1]];
            ans[i] = posR == -1 || posL == -1 || posR >= posL ? 0 : preSum[posL] - preSum[posR];
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "**|**|***|";
        int[][] q = {{2, 5}, {5, 9}};
        int[] ans = platesBetweenCandles(s, q);
        for (int an : ans) {
            System.out.println(an);
        }
    }
}
