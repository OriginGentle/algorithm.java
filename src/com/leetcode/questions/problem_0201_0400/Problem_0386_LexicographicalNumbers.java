package com.leetcode.questions.problem_0201_0400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/4/18
 */
public class Problem_0386_LexicographicalNumbers {

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < n; i++) {
            ans.add(num);
            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return ans;
    }
}
