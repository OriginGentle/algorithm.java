package com.basic.day16;

import com.basic.day16.graph.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Author ycb
 * @Date 2021/3/16-11:28
 * @Description 图的深度优先遍历
 * <p>
 * 1.利用栈实现
 * 2.从源节点开始把节点按照深度放入栈，然后弹出
 * 3.每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 * 4.直到栈变空
 * <p>
 * 关键：
 * 一条路走完
 */
public class Code02_DFS {
    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        // 入栈就打印
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    // 栈即整条路径
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
