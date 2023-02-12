package com.leetcode.questions.problem_0401_0600;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ycb
 * @date 2022/3/12-19:07
 */
public class Problem_0590_NAryTreePostorderTraversal {

    public static class Node {
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

    public List<Integer> postorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        process(root, res);
        return res;
    }

    private void process(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node node : root.children) {
            process(node, res);
        }
        res.add(root.val);
    }

    /*
    ====================================================================================================================
     */

    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        Deque<Object[]> stack = new ArrayDeque<>();
        stack.addLast(new Object[]{0, root});
        while (!stack.isEmpty()) {
            Object[] cur = stack.pollLast();
            int loc = (int) cur[0];
            Node t = (Node) cur[1];
            if (t == null) continue;
            if (loc == 0) {
                stack.addLast(new Object[]{1, t});
                int n = t.children.size();
                for (int i = n - 1; i >= 0; i--) {
                    stack.addLast(new Object[]{0, t.children.get(i)});
                }
            } else if (loc == 1) {
                res.add(t.val);
            }
        }
        return res;
    }
}
