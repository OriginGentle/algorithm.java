package com.leetcode.problem_interview;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/9/28-11:24
 */
public class Problem_17_09_GetKthMagicNumberLcci {

    private static final int[] cand = {3, 5, 7};

    public static int getKthMagicNumber1(int k) {
        PriorityQueue<Long> heap = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        heap.add(1L);
        set.add(1L);
        for (int i = 1; i <= k; i++) {
            long cur = heap.poll();
            if (i == k)
                return (int) cur;

            for (int c : cand) {
                long next = cur * c;
                if (!set.contains(next)) {
                    set.add(next);
                    heap.add(next);
                }
            }
        }
        return -1;
    }

    /*
    ====================================================================================================================
     */

    public static int getKthMagicNumber2(int k) {
        int[] ans = new int[k + 1];
        ans[1] = 1;
        for (int i3 = 1, i5 = 1, i7 = 1, idx = 2; idx <= k; idx++) {

            int a = ans[i3] * 3, b = ans[i5] * 5, c = ans[i7] * 7;

            int min = Math.min(a, Math.min(b, c));

            if (min == a) i3++;
            if (min == b) i5++;
            if (min == c) i7++;

            ans[idx] = min;
        }
        return ans[k];
    }
}
