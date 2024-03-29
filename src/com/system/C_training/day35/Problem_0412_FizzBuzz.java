package com.system.C_training.day35;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/23-13:25
 */
public class Problem_0412_FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                ans.add("FizzBuzz");
            } else if (i % 5 == 0) {
                ans.add("Buzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else {
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }
}
