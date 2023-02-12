package com.leetcode.questions.problem_2001_2200;

import java.util.Arrays;

/**
 * @author ycb
 * @date 2022/12/31-18:43
 */
public class Problem_2037_MinimumNumberOfMovesToSeatEveryone {

    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int ans = 0;
        for (int i = 0; i < seats.length; i++) {
            ans += Math.abs(seats[i] - students[i]);
        }
        return ans;
    }
}
