package com.system.IV_weekly.code_2022_02_4_week;

/**
 * @author ycb
 * @date 2022/2/26-12:19
 * @description 来自微软
 * 给定一个正数num，要返回一个大于num的数，并且每一位和相邻位的数字不能相等
 * 返回达标的数字中，最小的那个。
 * 10^9
 */
public class Code02_NearBiggerNoSameNeighbour {

    public static int near(int num) {
        char[] str = ("0" + (num + 1)).toCharArray();
        process(str);
        return Integer.valueOf(String.valueOf(str));
    }

    private static void process(char[] str) {
        for (int i = 1; i < str.length; i++) {
            if (str[i - 1] == str[i]) {
                addOne(str, i);
                for (int j = i + 1; j < str.length; j++) {
                    str[j] = '0';
                }
                process(str);
                return;
            }
        }
    }

    private static void addOne(char[] str, int i) {
        boolean up = true;
        while (up && str[i] == '9') {
            str[i--] = '0';
        }
        str[i]++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int num = (int) (Math.random() * 1000);
            System.out.println(num);
            int ans = near(num);
            System.out.println(ans);
            System.out.println();
        }
    }
}
