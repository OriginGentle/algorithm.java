package com.leetcode.problem_1801_2000;

/**
 * @author ycb
 * @date 2023/1/9-09:24
 */
public class Problem_1803_CountPairsWithXorInARange {

    // 范围 0 - 14,共15位
    public static final int HIGH_BIT = 14;

    public static TrieNode root = null;

    public static int countPairs(int[] nums, int low, int high) {
        return func(nums, high) - func(nums, low - 1);
    }

    private static int func(int[] nums, int target) {
        root = new TrieNode();
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            add(nums[i - 1]);
            res += get(nums[i], target);
        }
        return res;
    }

    private static int get(int num, int target) {
        TrieNode cur = root;
        int sum = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int r = (num >> k) & 1;
            if (((target >> k) & 1) != 0) {
                if (cur.next[r] != null) {
                    sum += cur.next[r].pass;
                }
                if (cur.next[r ^ 1] == null) {
                    return sum;
                }
                cur = cur.next[r ^ 1];
            } else {
                if (cur.next[r] == null) {
                    return sum;
                }
                cur = cur.next[r];
            }
        }
        sum += cur.pass;
        return sum;
    }

    private static void add(int num) {
        TrieNode cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (cur.next[bit] == null) {
                cur.next[bit] = new TrieNode();
            }
            cur = cur.next[bit];
            cur.pass++;
        }
    }

    public static class TrieNode {
        public int pass;
        public TrieNode[] next;

        public TrieNode() {
            pass = 0;
            next = new TrieNode[2];
        }
    }

    public static void main(String[] args) {
        // 1 - 200004
        int max = 1 << (HIGH_BIT);
        System.out.println(max);
    }
}
