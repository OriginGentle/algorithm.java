package com.leetcode.problem_finger_offer;

/**
 * @author ycb
 * @date 2022/7/16-23:17
 */
public class Problem_041_qIsx9U {

    class MovingAverage {

        private final int[] arr = new int[10010];
        private int n, sum, l, r;

        /**
         * Initialize your data structure here.
         */
        public MovingAverage(int size) {
            n = size;
        }

        public double next(int val) {
            sum += arr[r++] = val;
            if (r - l > n)
                sum -= arr[l++];
            return sum * 1.0 / (r - l);
        }
    }
}
