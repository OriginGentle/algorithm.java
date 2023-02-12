package com.leetcode.questions.problem_0601_0800;

import java.util.*;

/**
 * @author ycb
 * @date 2022/9/5-09:02
 */
public class Problem_0652_FindDuplicateSubtrees {

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

    private List<TreeNode> res;
    private int idx;
    private Map<Mark, Integer> map;
    private Set<Integer> set;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new ArrayList<>();
        idx = 1;
        map = new HashMap<>();
        set = new HashSet<>();

        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        int left = 0, right = 0;
        if (node.left != null)
            left = dfs(node.left);
        if (node.right != null)
            right = dfs(node.right);

        Mark mark = new Mark(node.val, left, right);
        if (map.containsKey(mark)) {
            int cur = map.get(mark);
            if (!set.contains(cur)) {
                set.add(cur);
                res.add(node);
            }
            return cur;
        } else {
            map.put(mark, idx++);
            return idx - 1;
        }
    }

    public static class Mark {
        public int nodeVal, leftIdx, rightIdx;

        public Mark(int nodeVal, int leftIdx, int rightIdx) {
            this.nodeVal = nodeVal;
            this.leftIdx = leftIdx;
            this.rightIdx = rightIdx;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Mark)) {
                return false;
            }
            Mark mark = (Mark) other;
            return nodeVal == mark.nodeVal && leftIdx == mark.leftIdx && rightIdx == mark.rightIdx;
        }

        @Override
        public int hashCode() {
            return (nodeVal + 200) * 100000000 + leftIdx * 10000 + rightIdx;
        }
    }
}
