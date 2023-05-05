package com.leetcode.questions.problem_2201_2400;

/**
 * @author ycb
 * @date 2023/5/5-08:44
 */
public class Problem_2432_TheEmployeeThatWorkedOnTheLongestTask {

    public int hardestWorker(int n, int[][] logs) {
        int emp = -1, maxWorkTime = 0;
        int curTime = 0;
        for (int[] log : logs) {
            int workTime = log[1] - curTime;
            if (workTime > maxWorkTime) {
                maxWorkTime = workTime;
                emp = log[0];
            } else if (workTime == maxWorkTime) {
                emp = Math.min(emp, log[0]);
            }
            curTime = log[1];
        }
        return emp;
    }
}
