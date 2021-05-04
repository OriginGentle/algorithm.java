package com.algorithm.day16.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ycb
 * @Date 2021/3/16-11:37
 * @Description 点结构的表示
 */
public class Node {
    public int value;
    public int in; // 入度
    public int out; // 出度
    public List<Node> nexts; // 邻居
    public List<Edge> edges; // 边

    public Node(int value){
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
