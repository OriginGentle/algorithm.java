package com.algorithm.day16;

import com.algorithm.day16.graph.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/3/16-11:28
 * @Description 图的深度优先遍历
 */
public class DFS {
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
