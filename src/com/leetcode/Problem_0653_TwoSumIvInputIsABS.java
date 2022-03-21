package com.leetcode;

import java.util.*;

/**
 * @author ycb
 * @date 2022/3/21-8:09
 */
public class Problem_0653_TwoSumIvInputIsABS {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    Set<Integer> set = new HashSet<>();

    public boolean findTarget1(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget1(root.left, k) || findTarget1(root.right, k);
    }

    /*
    ====================================================================================================================
     */

    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (set.contains(k - cur.val)) {
                return true;
            }
            set.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return false;
    }

    /*
    ====================================================================================================================
     */

    List<Integer> in = new ArrayList<>();

    public boolean findTarget3(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        process(root);
        int L = 0, R = in.size() - 1;
        while (L < R) {
            if (in.get(L) + in.get(R) == k) {
                return true;
            } else if (in.get(L) + in.get(R) > k) {
                R--;
            } else {
                L++;
            }
        }
        return false;
    }

    public void process(TreeNode node) {
        if (node == null) {
            return;
        }
        process(node.left);
        in.add(node.val);
        process(node.right);
    }
}
