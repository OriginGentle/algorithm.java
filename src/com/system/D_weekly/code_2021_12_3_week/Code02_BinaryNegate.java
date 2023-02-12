package com.system.D_weekly.code_2021_12_3_week;

/**
 * @author ycb
 * @date 2021/12/18-14:04
 * @description https://www.nowcoder.com/test/33701596/summary
 */
public class Code02_BinaryNegate {

    public static String maxLexicographical(String num) {
        char[] str = num.toCharArray();
        int i = 0;
        while (i < str.length) {
            if (str[i] == '0') {
                break;
            }
            i++;
        }
        while (i < str.length) {
            if (str[i] == '1') {
                break;
            }
            str[i++] = '1';
        }
        return String.valueOf(str);
    }
}
