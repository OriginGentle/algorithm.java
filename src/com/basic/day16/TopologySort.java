package com.basic.day16;

import com.basic.day16.graph.Graph;
import com.basic.day16.graph.Node;

import java.util.*;

/**
 * @Author ycb
 * @Date 2021/3/16-16:02
 * @Description 拓扑排序
 * <p>
 * 1）在图中找到所有入度为0的点输出
 * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 */
public class TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        // key 某个节点   value 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 只有剩余入度为0的点，才进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>(); // 集合加入的顺序就是拓扑序
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) { // 遍历弹出节点的所有邻居
                inMap.put(next, inMap.get(next) - 1); // 更新所有的邻居的入度
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next); // 邻居的入度为0加入队列
                }
            }
        }
        return result;
    }
}
