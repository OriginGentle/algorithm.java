package com.system.C_training.day30;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/16-21:46
 */
public class Problem_0119_PascalTriangleII {

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i - 1; j > 0; j--) {
                ans.set(j, ans.get(j) + ans.get(j - 1));
            }
            ans.add(1);
        }
        return ans;
    }
}
