package com.leetcode.problem_1401_1600;

/**
 * @author ycb
 * @since 2022/3/17-13:55
 */
public class Problem_1491_AverageSalaryExcludingTheMinimumAndMaximumSalary {

    public double average(int[] salary) {
        double ans = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int n = salary.length;
        for (int s : salary) {
            ans += s;
            max = Math.max(max, s);
            min = Math.min(min, s);
        }
        return (ans - max - min) / (n - 2);
    }
}
