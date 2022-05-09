package com.leetcode.problem_0001_0200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/5/5-11:23
 */
public class Problem_0137_SingleNumberII {

    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                ans = entry.getKey();
                break;
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /*
    ====================================================================================================================
     */

    public static int singleNumber3(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            b = ~a & (b ^ num);
            a = ~b & (a ^ num);
        }
        return b;
    }
}
