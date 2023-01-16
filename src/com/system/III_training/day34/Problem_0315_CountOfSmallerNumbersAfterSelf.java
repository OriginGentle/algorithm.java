package com.system.III_training.day34;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/22-9:23
 */
public class Problem_0315_CountOfSmallerNumbersAfterSelf {

    public static class Node {
        public int value;
        public int index;

        public Node(int v, int i) {
            value = v;
            index = i;
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null) {
            return ans;
        }
        for (int i = 0; i < nums.length; i++) {
            ans.add(0);
        }
        if (nums.length < 2) {
            return ans;
        }
        Node[] arr = new Node[nums.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node(nums[i], i);
        }
        process(arr, 0, arr.length - 1, ans);
        return ans;
    }

    public static void process(Node[] arr, int L, int R, List<Integer> ans) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M, ans);
        process(arr, M + 1, R, ans);
        merge(arr, L, M, R, ans);
    }

    public static void merge(Node[] arr, int L, int M, int R, List<Integer> ans) {
        Node[] help = new Node[R - L + 1];
        int i = help.length - 1;
        int p1 = M, p2 = R;
        while (p1 >= L && p2 >= M + 1) {
            if (arr[p1].value > arr[p2].value) {
                ans.set(arr[p1].index, ans.get(arr[p1].index) + p2 - M);
            }
            help[i--] = arr[p1].value > arr[p2].value ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 >= M + 1) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
}
