package com.leetcode.questions.problem_1601_1800;

import java.util.HashMap;

/**
 * @author ycb
 * @date 2022/11/23-08:26
 */
public class Problem_1742_MaximumNumberOfBallsInABox {

    public int countBalls(int lowLimit, int highLimit) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int num = bitCount(i);
            map.put(num, map.getOrDefault(num, 0) + 1);
            ans = Math.max(map.get(num), ans);
        }
        return ans;
    }

    public int bitCount(int num) {
        int ans = 0;
        while (num != 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }
}
