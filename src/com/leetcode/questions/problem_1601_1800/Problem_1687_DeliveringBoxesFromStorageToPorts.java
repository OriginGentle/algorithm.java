package com.leetcode.questions.problem_1601_1800;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ycb
 * @date 2022/12/5-08:25
 */
public class Problem_1687_DeliveringBoxesFromStorageToPorts {

    public static int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] p = new int[n + 1];
        int[] w = new int[n + 1];
        int[] neg = new int[n + 1];
        long[] wPreSum = new long[n + 1];
        // boxes[i] = {a, b}
        // a = 需要送达的码头
        // b = 箱子的重量
        for (int i = 1; i <= n; i++) {
            p[i] = boxes[i - 1][0];
            w[i] = boxes[i - 1][1];
            if (i > 1) {
                neg[i] = neg[i - 1] + (p[i] != p[i - 1] ? 1 : 0);
            }
            wPreSum[i] = wPreSum[i - 1] + w[i];
        }

        Deque<Integer> op = new ArrayDeque<>();
        op.offerLast(0);
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            while (i - op.peekFirst() > maxBoxes ||
                    wPreSum[i] - wPreSum[op.peekFirst()] > maxWeight) {
                op.pollFirst();
            }

            f[i] = g[op.peekFirst()] + neg[i] + 2;

            if (i != n) {
                g[i] = f[i] - neg[i + 1];
                while (!op.isEmpty() && g[i] <= g[op.peekLast()]) {
                    op.pollLast();
                }
                op.offerLast(i);
            }
        }
        return f[n];
    }
}
