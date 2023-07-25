package com.leetcode.questions.problem_2201_2400;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2023/7/25-21:38
 */
public class Problem_2208_MinimumOperationsToHalveArraySum {

    public static int halveArray(int[] nums) {
        double sum = 0, halfSum = 0;
        PriorityQueue<Double> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            sum += num;
            heap.add((double) num);
        }

        int ans = 0;
        while (halfSum < sum / 2) {
            double cur = heap.poll();
            double halfNum = cur / 2;
            halfSum += halfNum;
            heap.add(halfNum);
            ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 19, 8, 1};
        int ans = halveArray(arr);
        System.out.println(ans);
    }
}
