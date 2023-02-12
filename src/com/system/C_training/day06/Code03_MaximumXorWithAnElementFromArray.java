package com.system.C_training.day06;

/**
 * @author ycb
 * @date 2021/8/13-8:17
 * @description https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
 */
public class Code03_MaximumXorWithAnElementFromArray {

    public static int[] maximizeXor(int[] nums, int[][] queries) {
        NumTrie numTrie = new NumTrie();
        for (int i = 0; i < nums.length; i++) {
            numTrie.add(nums[i]);
        }
        int M = queries.length;
        int[] ans = new int[M];
        for (int i = 0; i < M; i++) {
            ans[i] = numTrie.maxXorWithXBehindM(queries[i][0], queries[i][1]);
        }
        return ans;
    }

    // 树上的节点
    public static class Node {
        public int min;
        public Node[] nexts;

        public Node() {
            this.min = Integer.MAX_VALUE;
            nexts = new Node[2];
        }
    }

    // 前缀树结构
    public static class NumTrie {

        Node head = new Node();

        public void add(int num) {
            Node cur = head;
            head.min = Math.min(head.min, num);
            // 题目说明了是非负数组
            for (int move = 30; move >= 0; move--) {
                int path = (num >> move) & 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
                cur.min = Math.min(cur.min, num);
            }
        }

        // 前缀树中已经收集了一票数字
        // 哪个数与X异或的结果最大，把结果返回
        // 但是，只有<=m的数字，可以被考虑
        public int maxXorWithXBehindM(int X, int M) {
            if (head.min > M) {
                return -1;
            }
            // 一定存在某个数可以与X结合
            Node cur = head;
            int ans = 0;
            for (int move = 30; move >= 0; move--) {
                int path = (X >> move) & 1;
                // 期望遇到的东西
                int best = path ^ 1;
                // 实际遇到的东西
                best ^= (cur.nexts[best] == null || cur.nexts[best].min > M) ? 1 : 0;
                ans |= (best ^ path) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

}
