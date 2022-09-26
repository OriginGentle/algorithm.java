package com.leetcode.problem_0201_0400;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2022/9/26-14:40
 */
public class Problem_0246_UglyNumberII {

    private final int[] cand = {2, 3, 5};

    public int nthUglyNumber1(int n) {
        if (n == 1)
            return 1;

        HashSet<Long> set = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        set.add(1L);
        heap.add(1L);
        for (int i = 1; i <= n; i++) {
            long x = heap.poll();
            if (i == n)
                return (int) x;

            for (int c : cand) {
                long next = x * c;
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

    public int nthUglyNumber2(int n) {
        int[] ans = new int[n + 1];
        ans[1] = 1;

        for (int i2 = 1, i3 = 1, i5 = 1, idx = 2; idx <= n; idx++) {
            int a = ans[i2] * 2, b = ans[i3] * 3, c = ans[i5] * 5;

            int min = Math.min(a, Math.min(b, c));

            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;

            ans[idx] = min;

        }
        return ans[n];
    }
}
