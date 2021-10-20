package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/13-8:19
 */
public class Problem_0412_FizzBuzz {

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                ans.add("Fizz");
            } else if (i % 3 != 0 && i % 5 == 0) {
                ans.add("Buzz");
            } else if (i % 3 == 0 && i % 5 == 0) {
                ans.add("FizzBuzz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }
}
