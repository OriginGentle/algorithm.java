package com.leetcode.questions.problem_1401_1600;

/**
 * @author ycb
 * @date 2022/8/19-08:25
 */
public class Problem_1450_NumberOfStudentsDoingHomeworkAtAGivenTime {

    public int size = 10001;

    public int[] cmt;

    public int busyStudent1(int[] startTime, int[] endTime, int queryTime) {
        cmt = new int[size];
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            int start = startTime[i], end = endTime[i];
            build(start, end);
        }
        return cmt[queryTime];
    }

    private void build(int start, int end) {
        for (int i = start; i <= end; i++) {
            cmt[i]++;
        }
    }

    /*
    ====================================================================================================================
     */

    public int busyStudent2(int[] startTime, int[] endTime, int queryTime) {
        int ans = 0;
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                ans++;
            }
        }
        return ans;
    }
}
