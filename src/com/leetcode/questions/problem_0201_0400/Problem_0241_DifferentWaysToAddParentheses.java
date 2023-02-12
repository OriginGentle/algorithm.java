package com.leetcode.questions.problem_0201_0400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/7/2-23:55
 */
public class Problem_0241_DifferentWaysToAddParentheses {

    public static List<Integer> diffWaysToCompute(String s) {
        char[] str = s.toCharArray();
        return dfs(str, 0, str.length - 1);
    }

    public static List<Integer> dfs(char[] str, int l, int r) {
        List<Integer> ans = new ArrayList<>();

        for (int i = l; i <= r; i++) {
            if (str[i] >= '0' && str[i] <= '9')
                continue;

            List<Integer> left = dfs(str, l, i - 1);
            List<Integer> right = dfs(str, i + 1, r);

            for (int x : left) {
                for (int y : right) {
                    int cur = 0;
                    if (str[i] == '+')
                        cur = x + y;
                    else if (str[i] == '-')
                        cur = x - y;
                    else
                        cur = x * y;

                    ans.add(cur);
                }
            }
        }

        if (ans.isEmpty()) {
            int cur = 0;
            for (int i = l; i <= r; i++)
                cur = cur * 10 + (str[i] - '0');
            ans.add(cur);
        }
        return ans;
    }
}
