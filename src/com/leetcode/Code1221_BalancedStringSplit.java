package com.leetcode;

/**
 * @author ycb
 * @date 2021/9/7-10:18
 */
public class Code1221_BalancedStringSplit {

    public int balancedStringSplit(String s) {
        int count = 0;
        int ans = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'R') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                ans++;
            }
        }
        return ans;
    }
}

