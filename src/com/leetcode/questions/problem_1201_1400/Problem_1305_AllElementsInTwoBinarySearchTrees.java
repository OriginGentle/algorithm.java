package com.leetcode.questions.problem_1201_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @date 2022/5/1-12:27
 */
public class Problem_1305_AllElementsInTwoBinarySearchTrees {

    public static class TreeNode {
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

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        process(root1, l1);
        List<Integer> l2 = new ArrayList<>();
        process(root2, l2);
        List<Integer> ans = new ArrayList<>();
        int n = l1.size(), m = l2.size(), i = 0, j = 0;
        while (i < n && j < m) {
            int a = l1.get(i);
            int b = l2.get(j);
            if (a <= b) {
                ans.add(a);
                i++;
            } else {
                ans.add(b);
                j++;
            }
        }
        while (i < n) {
            ans.add(l1.get(i++));
        }
        while (j < m) {
            ans.add(l2.get(j++));
        }
        return ans;
    }

    public static void process(TreeNode node, List<Integer> path) {
        if (node == null) {
            return;
        }
        process(node.left, path);
        path.add(node.val);
        process(node.right, path);
    }
}
