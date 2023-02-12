package com.leetcode.questions.problem_0801_1000;

/**
 * @author ycb
 * @date 2022/5/12-10:37
 * @desc
 */
public class Problem_0944_DeleteColumnsToMakeSorted {

    public static int minDeletionSize1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int n = strs.length;
        int m = strs[0].length();
        char[][] str = new char[n][m];
        for (int i = 0; i < n; i++) {
            str[i] = strs[i].toCharArray();
        }
        int ans = 0;
        for (int col = 0; col < m; col++) {
            for (int row = 1; row < n; row++) {
                if (str[row][col] - str[row - 1][col] < 0) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int minDeletionSize2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int n = strs.length;
        int m = strs[0].length();
        int ans = 0;
        for (int col = 0; col < m; col++) {
            for (int row = 1; row < n; row++) {
                if (strs[row].charAt(col) - strs[row - 1].charAt(col) < 0) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] str = {"cba", "daf", "ghi"};
        int ans = minDeletionSize1(str);
        System.out.println(ans);
    }
}
