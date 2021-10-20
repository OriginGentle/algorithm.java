package com.leetcode.interview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @since 2021/9/3-20:14
 */
public class Problem_17_14_SmallestK {

    // O(N * logK)
    public static int[] smallestK1(int[] arr, int k) {
        int[] ans = new int[k];
        if (arr == null || arr.length == 0 || k == 0) {
            return ans;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.add(arr[i]);
            }

        }
        int index = 0;
        while (!queue.isEmpty()) {
            ans[index++] = queue.poll();
        }
        return ans;
    }

}
