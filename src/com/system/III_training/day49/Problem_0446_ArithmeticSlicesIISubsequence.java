package com.system.III_training.day49;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ycb
 * @since 2021/11/12-8:57
 */
public class Problem_0446_ArithmeticSlicesIISubsequence {

    public static int numberOfArithmeticSlices(int[] nums) {
        int N = nums.length;
        int ans = 0;
        ArrayList<HashMap<Integer, Integer>> maps = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            maps.add(new HashMap<>());
            for (int j = i - 1; j >= 0; j--) {
                long diff = (long) nums[i] - (long) nums[j];
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) {
                    continue;
                }
                int dif = (int) diff;
                int count = maps.get(j).getOrDefault(dif, 0);
                ans += count;
                maps.get(i).put(dif, maps.get(i).getOrDefault(dif, 0) + count + 1);
            }
        }
        return ans;
    }
}
