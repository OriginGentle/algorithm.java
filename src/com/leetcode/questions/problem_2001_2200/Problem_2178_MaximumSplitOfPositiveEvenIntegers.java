package com.leetcode.questions.problem_2001_2200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2023/7/6-09:18
 */
public class Problem_2178_MaximumSplitOfPositiveEvenIntegers {

    public static List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 != 0) {
            return ans;
        }

        for (long i = 2; i <= finalSum; i += 2) {

            ans.add(i);
            finalSum -= i;
        }

        ans.set(ans.size() - 1, ans.get(ans.size() - 1) + finalSum);
        return ans;
    }
}
