package com.training.day06;

/**
 * @author ycb
 * @date 2021/8/13-8:17
 * @description https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class Code02_MaximumXorOfTwoNumbersInAnArray {

    public static int findMaximumXOR(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        NumTrie numTrie = new NumTrie();
        numTrie.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, numTrie.maxXor(arr[i]));
            numTrie.add(arr[i]);
        }
        return max;
    }

    public static class Node {
        public Node[] nexts = new Node[2];
    }

    // 前缀树
    public static class NumTrie {
        public Node head = new Node();

        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = (newNum >> move) & 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int num) {
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;
                int best = move == 31 ? path : path ^ 1;
                best = cur.nexts[best] != null ? best : best ^ 1;
                ans |= (best ^ path) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

}
