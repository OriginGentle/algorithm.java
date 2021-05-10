package com.algorithm.day16.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ycb
 * @Date 2021/3/16-11:37
 * @Description 点结构的描述
 */
public class Node {
    public int value;
    public int in; // 入度 --> 别人有多少边指向自己
    public int out; // 出度 --> 自己有多少边指向别人
    public List<Node> nexts; // 邻居 --> 从自身出发可以直接到达的节点
    public List<Edge> edges; // 边 --> 从自身出发可以直接到达节点的路径

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
