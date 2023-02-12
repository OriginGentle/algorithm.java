package com.system.B_basic.day16.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author ycb
 * @Date 2021/3/16-11:38
 * @Description 图结构的描述
 */
public class Graph {
    public HashMap<Integer, Node> nodes; // 点集
    public HashSet<Edge> edges; // 边集

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
