package com.leetcode.problem_0201_0400;


import java.util.LinkedList;

/**
 * @author ycb
 * @date 2022/8/11-09:22
 */
public class Problem_0239_SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] arr, int k) {
        if (arr == null || k < 1 || arr.length < k) {
            return null;
        }
        // 双端队列，窗口最大值更新结构
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] ans = new int[arr.length - k + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]) {
                qMax.pollLast();
            }
            qMax.addLast(R);
            if (qMax.peekFirst() == R - k)
                qMax.pollFirst();
            if (R >= k - 1) {
                ans[index++] = arr[qMax.peekFirst()];
            }
        }
        return ans;
    }
}
