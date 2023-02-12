package com.system.C_training.day30;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/16-21:46
 */
public class Problem_0118_PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<>());
            ans.get(i).add(1);
        }
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < i; j++) {
                ans.get(i).add(ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1));
            }
            ans.get(i).add(1);
        }
        return ans;
    }
}
