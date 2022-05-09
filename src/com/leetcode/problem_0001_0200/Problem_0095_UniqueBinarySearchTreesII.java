package com.leetcode.problem_0001_0200;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/3/1-16:35
 */
public class Problem_0095_UniqueBinarySearchTreesII {

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

    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new LinkedList<>();
        }
        return process(1, n);
    }

    private static List<TreeNode> process(int start, int end) {
        List<TreeNode> ans = new LinkedList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        // 枚举所有的根节点
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftAll = process(start, i - 1);
            List<TreeNode> rightAll = process(i + 1, end);
            for (TreeNode left : leftAll) {
                for (TreeNode right : rightAll) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = left;
                    cur.right = right;
                    ans.add(cur);
                }
            }
        }
        return ans;
    }
}
