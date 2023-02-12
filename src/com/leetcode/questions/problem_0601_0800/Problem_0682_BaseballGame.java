package com.leetcode.questions.problem_0601_0800;

/**
 * @author ycb
 * @date 2022/3/26-12:10
 */
public class Problem_0682_BaseballGame {

    public static int calPoints(String[] ops) {
        int ans = 0;
        int n = ops.length;
        int[] score = new int[n];
        int size = 0;
        for (int i = 0; i < n; i++, size++) {
            switch (ops[i]) {
                case "+":
                    score[size] = score[size - 1] + score[size - 2];
                    break;
                case "C":
                    size -= 2;
                    break;
                case "D":
                    score[size] = score[size - 1] * 2;
                    break;
                default:
                    score[size] = Integer.parseInt(ops[i]);
                    break;
            }
        }
        for (int i = 0; i < size; i++) {
            ans += score[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] ops = {"5", "2", "C", "D", "+"};
        int ans = calPoints(ops);
        System.out.println(ans);
    }
}
