package com.leetcode.problem_0401_0600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycb
 * @since 2022/3/10-8:30
 */
public class Problem_0589_NAryTreePreorderTraversal {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    public static void process(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            process(root.children.get(i), ans);
        }
    }
}
