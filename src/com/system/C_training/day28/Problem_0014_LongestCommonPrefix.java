package com.system.C_training.day28;

/**
 * @author ycb
 * @since 2021/10/14-8:33
 */
public class Problem_0014_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char[] chs = strs[0].toCharArray();
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            char[] tmp = str.toCharArray();
            int index = 0;
            while (index < tmp.length && index < chs.length) {
                if (tmp[index] != chs[index]) {
                    break;
                }
                index++;
            }
            min = Math.min(min, index);
            if (min == 0) {
                return "";
            }
        }
        return strs[0].substring(0, min);
    }
}
