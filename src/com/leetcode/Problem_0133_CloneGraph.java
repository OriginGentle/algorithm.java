package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycb
 * @date 2022/5/1-19:40
 */
public class Problem_0133_CloneGraph {

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    static Map<Node, Node> visited = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);
        for (Node next : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(next));
        }
        return cloneNode;
    }
}
