package com.leetcode.questions.problem_1801_2000;

import java.util.Arrays;

/**
 * @Author ycb
 * @Date 2021/7/2-13:36
 */
public class Problem_1833_MaxIceCream {

    public int maxIceCream2(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int i = 0; i < costs.length; i++) {
            if (coins - costs[i] >= 0) {
                coins -= costs[i];
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /*
    ====================================================================================================================
     */

    public static int maxIceCream1(int[] costs, int coins) {
        int[] freq = new int[100001];
        for (int cost : costs) {
            freq[cost]++;
        }
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            if (coins >= i) {
                int curCount = Math.min(freq[i], coins / i);
                count += curCount;
                coins -= i * curCount;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 4, 1};
        int ans = maxIceCream1(arr, 7);
        System.out.println(ans);
    }

}
