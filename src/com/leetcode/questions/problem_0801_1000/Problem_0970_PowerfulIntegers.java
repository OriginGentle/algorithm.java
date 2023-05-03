package com.leetcode.questions.problem_0801_1000;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ycb
 * @date 2023/5/2-16:04
 */
public class Problem_0970_PowerfulIntegers {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> ans = new HashSet<>();
        int v1 = 1;
        for (int i = 0; i < 21; i++) {
            int v2 = 1;
            for (int j = 0; j < 21; j++) {
                int v = v1 + v2;
                if (v <= bound)
                    ans.add(v);
                else
                    break;

                v2 *= y;
            }

            if (v1 > bound)
                break;

            v1 *= x;
        }
        return new ArrayList<>(ans);
    }
}
