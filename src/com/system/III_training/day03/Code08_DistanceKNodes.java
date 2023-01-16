package com.system.III_training.day03;

import java.util.*;

/**
 * @Author ycb
 * @Date 2021/7/27-7:57
 * @Description 给定三个参数：
 * 二叉树的头节点head，树上某个节点target，正数K
 * 从target开始，可以向上走或者向下走
 * 返回与target的距离是K的所有节点
 */
public class Code08_DistanceKNodes {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            this.value = v;
        }
    }

    // 关键点:K代表想要的节点在第几层 --> 树的宽度优先遍历
    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(root, null);
        createParentMap(root, parentMap);
        // 队列:数的宽度优先遍历
        Queue<Node> queue = new LinkedList<>();
        // 看过的节点放进set中
        HashSet<Node> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        // k : 目标节点在第几层
        int curLevel = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 编程技巧:一次处理一批数
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == K) {
                    ans.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    queue.offer(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.offer(cur.right);
                    visited.add(cur.right);
                }
                // 当前节点的父亲不为空:此时cur还没有来到头节点
                if (parentMap.get(cur) != null && !visited.contains(parentMap.get(cur))) {
                    visited.add(parentMap.get(cur));
                    queue.offer(parentMap.get(cur));
                }
            }
            curLevel++;
            if (curLevel > K) {
                break;
            }
        }
        return ans;
    }

    public static void createParentMap(Node cur, HashMap<Node, Node> parentMap) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            parentMap.put(cur.left, cur);
            createParentMap(cur.left, parentMap);
        }
        if (cur.right != null) {
            parentMap.put(cur.right, cur);
            createParentMap(cur.right, parentMap);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }

}
