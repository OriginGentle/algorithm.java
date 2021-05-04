package com.algorithm.day16.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author ycb
 * @Date 2021/3/16-11:38
 * @Description 图结构的表示
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
