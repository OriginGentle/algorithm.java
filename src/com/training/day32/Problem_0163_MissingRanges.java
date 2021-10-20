package com.training.day32;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/19-8:18
 */
public class Problem_0163_MissingRanges {

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        for (int num : nums) {
            if (num > lower) {
                ans.add(missing(lower, num - 1));
            }
            if (num == upper) {
                return ans;
            }
            lower = num + 1;
        }
        if (lower <= upper) {
            ans.add(missing(lower, upper));
        }
        return ans;
    }

    public static String missing(int lower, int upper) {
        String left = String.valueOf(lower);
        String right = "";
        if (upper > lower) {
            right = "->" + right;
        }
        return left + right;
    }

}
