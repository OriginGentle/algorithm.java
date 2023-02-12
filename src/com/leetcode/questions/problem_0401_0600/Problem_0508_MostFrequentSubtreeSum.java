package com.leetcode.questions.problem_0401_0600;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ycb
 * @date 2022/6/19-16:13
 */
public class Problem_0508_MostFrequentSubtreeSum {

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

    public int max = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        dfs(root, cntMap);
        List<Integer> res = new ArrayList<>();
        for (int key : cntMap.keySet()) {
            if (cntMap.get(key) == max) {
                res.add(key);
            }
        }
        int size = res.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private int dfs(TreeNode node, HashMap<Integer, Integer> cntMap) {
        if (node == null) {
            return 0;
        }
        int curVal = node.val + dfs(node.left, cntMap) + dfs(node.right, cntMap);
        cntMap.put(curVal, cntMap.getOrDefault(curVal, 0) + 1);
        max = Math.max(max, cntMap.get(curVal));
        return curVal;
    }
}
