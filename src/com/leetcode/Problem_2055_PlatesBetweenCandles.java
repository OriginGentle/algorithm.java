package com.leetcode;

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
        int[] ans = new int[n];
        int sum = 0;
        for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
            if (s.charAt(i) == '|') p = i;
            if (s.charAt(j) == '|') q = j;
            left[i] = p;
            right[j] = q;
            if (s.charAt(i) == '*') preSum[i] = ++sum;
        }
        for (int i = 0; i < m; i++) {
            // 左侧蜡烛的位置
            int l = left[queries[i][0]];
            // 右侧蜡烛的位置
            int r = right[queries[i][1]];
            // 左侧得有蜡烛
            if (l != -1 && r <= l) ans[i] = preSum[r] - preSum[l];
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "**|**|***|";
        int[][] q = {{2, 5}, {5, 9}};
        int[] ans = platesBetweenCandles(s, q);
        System.out.println();
    }
}
