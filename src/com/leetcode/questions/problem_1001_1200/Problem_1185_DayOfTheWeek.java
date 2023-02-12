package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @since 2022/1/3-15:54
 */
public class Problem_1185_DayOfTheWeek {

    private static String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};

    public static String dayOfTheWeek(int day, int month, int year) {
        int ans = 4;
        for (int i = 1971; i < year; i++) {
            boolean isLeapYear = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
            ans += isLeapYear ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            ans += days[i - 1];
            if (i == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
                ans++;
            }
        }
        ans += day;
        return weeks[ans % 7];
    }
}
