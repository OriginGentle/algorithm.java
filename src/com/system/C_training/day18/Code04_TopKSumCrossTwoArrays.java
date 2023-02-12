package com.system.C_training.day18;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author ycb
 * @date 2021/9/18-18:43
 * @description https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
 */
public class Code04_TopKSumCrossTwoArrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            arr2[i] = sc.nextInt();
        }
        int[] topK = topKSum(arr1, arr2, K);
        for (int i = 0; i < K; i++) {
            System.out.print(topK[i] + " ");
        }
        System.out.println();
        sc.close();
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) {
            return null;
        }
        int N = arr1.length;
        int M = arr2.length;
        topK = Math.min(topK, N * M);
        int[] res = new int[topK];
        int resIndex = 0;
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapComp());
        HashSet<Long> set = new HashSet<>();
        int i1 = N - 1;
        int i2 = M - 1;
        maxHeap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));
        set.add(calculationIndex(i1, i2, M));
        while (resIndex != topK) {
            Node curNode = maxHeap.poll();
            res[resIndex++] = curNode.sum;
            i1 = curNode.index1;
            i2 = curNode.index2;
            set.remove(calculationIndex(i1, i2, M));
            if (i1 - 1 >= 0 && !set.contains(calculationIndex(i1 - 1, i2, M))) {
                set.add(calculationIndex(i1 - 1, i2, M));
                maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }
            if (i2 - 1 >= 0 && !set.contains(calculationIndex(i1, i2 - 1, M))) {
                set.add(calculationIndex(i1, i2 - 1, M));
                maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }
        }
        return res;
    }

    public static class Node {
        public int index1;
        public int index2;
        public int sum;

        public Node(int i1, int i2, int s) {
            index1 = i1;
            index2 = i2;
            sum = s;
        }
    }

    public static class MaxHeapComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static long calculationIndex(int i1, int i2, int M) {
        return (long) i1 * (long) M + (long) i2;
    }

}
