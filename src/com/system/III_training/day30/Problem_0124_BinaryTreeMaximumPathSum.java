package com.system.III_training.day30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author ycb
 * @since 2021/10/16-21:46
 */
public class Problem_0124_BinaryTreeMaximumPathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPathSum;
    }

    // 任何一棵树，必须汇报上来的信息
    public static class Info {
        public int maxPathSum;
        public int maxPathSumFromHead;

        public Info(int path, int head) {
            maxPathSum = path;
            maxPathSumFromHead = head;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // 必须经过包括头结点的最大路径和: 1)只有x 2)x往左扎 3)x往右扎
        int maxPathSumFromHead = x.val;
        if (leftInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, x.val + leftInfo.maxPathSumFromHead);
        }
        if (rightInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, x.val + rightInfo.maxPathSumFromHead);
        }
        // x整棵树最大路径和 1) 只有x 2)左树整体的最大路径和 3) 右树整体的最大路径和
        int maxPathSum = x.val;
        if (leftInfo != null) {
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
        }
        if (rightInfo != null) {
            maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
        }
        // 4) x只往左扎 5）x只往右扎
        maxPathSum = Math.max(maxPathSumFromHead, maxPathSum);
        // 6）一起扎
        if (leftInfo != null && rightInfo != null && leftInfo.maxPathSumFromHead > 0
                && rightInfo.maxPathSumFromHead > 0) {
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead + x.val);
        }
        return new Info(maxPathSum, maxPathSumFromHead);
    }

    /*
    ====================================================================================================================
     */

    // follow up:如果要求返回整个路径怎么做？

    public static class Data {
        public int maxAllSum;
        public TreeNode from;
        public TreeNode to;
        public int maxHeadSum;
        public TreeNode end;

        public Data(int a, TreeNode b, TreeNode c, int d, TreeNode e) {
            maxAllSum = a;
            from = b;
            to = c;
            maxHeadSum = d;
            end = e;
        }
    }

    public static List<TreeNode> getMaxSumPath(TreeNode head) {
        List<TreeNode> ans = new ArrayList<>();
        if (head != null) {
            Data data = func(head);
            HashMap<TreeNode, TreeNode> fmap = new HashMap<>();
            fmap.put(head, head);
            fatherMap(head, fmap);
            fillPath(fmap, data.from, data.to, ans);
        }
        return ans;
    }

    public static Data func(TreeNode x) {
        if (x == null) {
            return null;
        }
        Data leftInfo = func(x.left);
        Data rightInfo = func(x.right);
        int maxHeadSum = x.val;
        TreeNode end = x;
        if (leftInfo != null && leftInfo.maxHeadSum > 0 && (rightInfo == null || leftInfo.maxHeadSum > rightInfo.maxHeadSum)) {
            maxHeadSum += leftInfo.maxHeadSum;
            end = leftInfo.end;
        }
        if (rightInfo != null && rightInfo.maxHeadSum > 0 && (leftInfo == null || rightInfo.maxHeadSum > leftInfo.maxHeadSum)) {
            maxHeadSum += rightInfo.maxHeadSum;
            end = rightInfo.end;
        }
        int maxAllSum = Integer.MIN_VALUE;
        TreeNode from = null;
        TreeNode to = null;
        if (leftInfo != null) {
            maxAllSum = leftInfo.maxAllSum;
            from = leftInfo.from;
            to = leftInfo.to;
        }
        if (rightInfo != null && rightInfo.maxAllSum > maxAllSum) {
            maxAllSum = rightInfo.maxAllSum;
            from = rightInfo.from;
            to = rightInfo.to;
        }
        int p3 = x.val + (leftInfo != null && leftInfo.maxHeadSum > 0 ? leftInfo.maxHeadSum : 0)
                + (rightInfo != null && rightInfo.maxHeadSum > 0 ? rightInfo.maxHeadSum : 0);
        if (p3 > maxAllSum) {
            maxAllSum = p3;
            from = (leftInfo != null && leftInfo.maxHeadSum > 0) ? leftInfo.end : x;
            to = (rightInfo != null && rightInfo.maxHeadSum > 0) ? rightInfo.end : x;
        }
        return new Data(maxAllSum, from, to, maxHeadSum, end);
    }

    public static void fatherMap(TreeNode h, HashMap<TreeNode, TreeNode> map) {
        if (h.left == null && h.right == null) {
            return;
        }
        if (h.left != null) {
            map.put(h.left, h);
            fatherMap(h.left, map);
        }
        if (h.right != null) {
            map.put(h.right, h);
            fatherMap(h.right, map);
        }
    }

    public static void fillPath(HashMap<TreeNode, TreeNode> fmap, TreeNode a, TreeNode b, List<TreeNode> ans) {
        if (a == b) {
            ans.add(a);
        } else {
            HashSet<TreeNode> ap = new HashSet<>();
            TreeNode cur = a;
            while (cur != fmap.get(cur)) {
                ap.add(cur);
                cur = fmap.get(cur);
            }
            ap.add(cur);
            cur = b;
            TreeNode lca = null;
            while (lca == null) {
                if (ap.contains(cur)) {
                    lca = cur;
                } else {
                    cur = fmap.get(cur);
                }
            }
            while (a != lca) {
                ans.add(a);
                a = fmap.get(a);
            }
            ans.add(lca);
            ArrayList<TreeNode> right = new ArrayList<>();
            while (b != lca) {
                right.add(b);
                b = fmap.get(b);
            }
            for (int i = right.size() - 1; i >= 0; i--) {
                ans.add(right.get(i));
            }
        }
    }

    // for test
    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(-7);
        head.right = new TreeNode(-5);
        head.left.left = new TreeNode(9);
        head.left.right = new TreeNode(9);
        head.right.left = new TreeNode(4);
        head.right.right = new TreeNode(3);

        List<TreeNode> maxPath = getMaxSumPath(head);

        for (TreeNode n : maxPath) {
            System.out.println(n.val);
        }
    }
}
