package com.training.day28;

/**
 * @author ycb
 * @since 2021/10/14-8:32
 */
public class Problem_0012_IntegerToRoman {

    public static String intToRoman(int num) {
        String[][] c = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}};
        StringBuilder builder = new StringBuilder();
        builder
                .append(c[3][num / 1000 % 10])
                .append(c[2][num / 100 % 10])
                .append(c[1][num / 10 % 10])
                .append(c[0][num % 10]);
        return builder.toString();
    }

}
