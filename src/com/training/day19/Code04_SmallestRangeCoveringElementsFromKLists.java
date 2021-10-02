package com.training.day19;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author ycb
 * @date 2021/9/27-11:38
 * @description https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */
public class Code04_SmallestRangeCoveringElementsFromKLists {

    public static class Node {
        public int value;
        public int arrive;
        public int index;

        public Node(int v, int a, int i) {
            value = v;
            arrive = a;
            index = i;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.arrive - o2.arrive;
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();
        TreeSet<Node> orderSet = new TreeSet<>(new NodeComparator());
        for (int i = 0; i < N; i++) {
            orderSet.add(new Node(nums.get(i).get(0), i, 0));
        }
        boolean set = false;
        int a = 0, b = 0;
        while (orderSet.size() == N) {
            Node min = orderSet.first();
            Node max = orderSet.last();
            if (!set || max.value - min.value < b - a) {
                set = true;
                a = min.value;
                b = max.value;
            }
            min = orderSet.pollFirst();
            int arrive = min.arrive;
            int index = min.index + 1;
            if (index != nums.get(arrive).size()) {
                orderSet.add(new Node(nums.get(arrive).get(index), arrive, index));
            }
        }
        return new int[]{a, b};
    }
}
