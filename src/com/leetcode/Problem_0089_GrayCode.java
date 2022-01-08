package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/1/8-14:34
 */
public class Problem_0089_GrayCode {

    public static List<Integer> grayCode1(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        while (n-- > 0) {
            int m = ans.size();
            for (int i = m - 1; i >= 0; i--) {
                ans.set(i, ans.get(i) << i);
                ans.add(ans.get(i) + 1);
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static List<Integer> grayCode2(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ans.size();
            for (int j = m - 1; j >= 0; j--) {
                ans.add(ans.get(j) | (1 << (i - 1)));
            }
        }
        return ans;
    }
}
