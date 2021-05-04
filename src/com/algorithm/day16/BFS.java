package com.algorithm.day16;

import com.algorithm.day16.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ycb
 * @Date 2021/3/16-11:27
 * @Description 图的宽度优先遍历
 */
public class BFS {
    public static void bfs(Node start){
        if (start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
