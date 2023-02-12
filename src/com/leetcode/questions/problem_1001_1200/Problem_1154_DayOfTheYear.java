package com.leetcode.questions.problem_1001_1200;

/**
 * @author ycb
 * @since 2021/12/21-8:30
 */
public class Problem_1154_DayOfTheYear {

    public static int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int[] count = new int[13];

    static {
        for (int i = 1; i < 13; i++) {
            count[i] = count[i - 1] + monthDay[i - 1];
        }
    }

    public static int dayOfYear1(String date) {
        String[] str = date.split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);
        int base = isLeapYear(year) && month > 2 ? count[month - 1] + 1 : count[month - 1];
        return base + day;
    }

    /*
    ====================================================================================================================
     */

    public static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int dayOfYear2(String date) {
        String[] str = date.split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);
        if (isLeapYear(year)) {
            ++days[1];
        }
        int ans = 0;
        for (int i = 0; i < month - 1; i++) {
            ans += days[i];
        }
        return ans + day;
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }


    public static void main(String[] args) {
        String date = "2015-04-20";
        int ans1 = dayOfYear1(date);
        int ans2 = dayOfYear2(date);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
