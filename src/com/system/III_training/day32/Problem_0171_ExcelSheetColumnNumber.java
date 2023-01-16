package com.system.III_training.day32;

/**
 * @author ycb
 * @since 2021/10/19-8:18
 */
public class Problem_0171_ExcelSheetColumnNumber {

    public static int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            ans = ans * 26 + (columnTitle.charAt(i) - 'A') + 1;
        }
        return ans;
    }
}
