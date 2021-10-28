package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author ycb
 * @since 2021/10/26-10:48
 */
public class Problem_0496_NextGreaterElementI {

    // 单调栈
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int M = nums1.length, N = nums2.length;
        Stack<Integer> stack = new Stack<>();
        // 从右往左遍历
        // 单调栈，栈顶到栈底的顺序为 小 --> 大
        // 找一个数的右边第一个比它大的数
        for (int i = N - 1; i >= 0; i--) {
            int cur = nums2[i];
            // 当前数 >=  栈顶元素
            while (!stack.isEmpty() && cur >= stack.peek()) {
                stack.pop();
            }
            map.put(cur, stack.isEmpty() ? -1 : stack.peek());
            stack.push(cur);
        }
        for (int i = 0; i < M; i++) {
            nums1[i] = map.get(nums1[i]);
        }
        return nums1;
    }
}
