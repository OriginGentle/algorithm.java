package com.basic.day16;

import com.basic.day16.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author ycb
 * @Date 2021/3/16-11:27
 * @Description 图的宽度优先遍历
 * <p>
 * 1.利用队列实现
 * 2.从源节点开始依次按照宽度进队列，然后弹出
 * 3.每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
 * 4.直到队列变空
 */
public class BFS {
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        // 把start节点加入Queue和HashSet中
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            // 遍历弹出节点的所有邻居
            for (Node next : cur.nexts) {
                // set中没有的才加入队列，避免回路现象问题
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
