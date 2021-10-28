package com.training.day38;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ycb
 * @since 2021/10/25-10:59
 */
public class Problem_0739_DailyTemperatures {

    public int[] dailyTemperatures(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        // 单调栈
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            // 记录位置
            while (!stack.isEmpty() && arr[stack.peek().get(0)] < arr[i]) {
                List<Integer> popIs = stack.pop();
                for (Integer popI : popIs) {
                    ans[popI] = i - popI;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        return ans;
    }
}
