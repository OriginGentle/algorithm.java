package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @date 2023/4/11-10:14
 */
public class Problem_1041_RobotBoundedInCircle {


    public boolean isRobotBounded(String instructions) {
        int[] dist = new int[4];
        int k = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char cur = instructions.charAt(i);
            if (cur == 'L') {
                k = (k + 1) % 4;
            } else if (cur == 'R') {
                k = (k + 3) % 4;
            } else {
                dist[k]++;
            }
        }
        return k != 0 || (dist[0] == dist[2] && dist[1] == dist[3]);
    }
}
