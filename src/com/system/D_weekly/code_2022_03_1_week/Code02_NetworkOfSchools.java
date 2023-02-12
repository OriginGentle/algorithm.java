package com.system.D_weekly.code_2022_03_1_week;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ycb
 * @date 2022/3/5-12:11
 * @description http://poj.org/problem?id=1236
 * N个学校之间有单向的网络，每个学校得到一套软件后，可以通过单向网络向周边的学校传输
 * 问题1：初始至少需要向多少个学校发放软件，使得网络内所有的学校最终都能得到软件
 * 问题2：至少需要添加几条传输线路(边)，使任意向一个学校发放软件后
 * 经过若干次传送，网络内所有的学校最终都能得到软件
 * 2 <= N <= 1000
 * 从题意中抽象出的算法模型, 给定一个有向图，求：
 * 1) 至少要选几个顶点，才能做到从这些顶点出发，可以到达全部顶点
 * 2) 至少要加多少条边，才能使得从任何一个顶点出发，都能到达全部顶点
 */
public class Code02_NetworkOfSchools {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= n; i++) {
                edges.add(new ArrayList<Integer>());
            }
            for (int from = 1; from <= n; from++) {
                int to = 0;
                while ((to = sc.nextInt()) != 0) {
                    edges.get(from).add(to);
                }
            }
            StronglyConnectedComponents scc = new StronglyConnectedComponents(edges);
            int sccn = scc.getSccn();
            int[] in = new int[sccn + 1];
            int[] out = new int[sccn + 1];
            ArrayList<ArrayList<Integer>> dag = scc.getShortGraph();
            for (int i = 1; i <= sccn; i++) {
                for (int j : dag.get(i)) {
                    out[i]++;
                    in[j]++;
                }
            }
            int zeroIn = 0;
            int zeroOut = 0;
            for (int i = 1; i <= sccn; i++) {
                if (in[i] == 0) {
                    zeroIn++;
                }
                if (out[i] == 0) {
                    zeroOut++;
                }
            }
            System.out.println(zeroIn);
            System.out.println(sccn == 1 ? 0 : Math.max(zeroIn, zeroOut));

        }
        sc.close();
    }

    // 时间复杂度 O(N + M) 其中N:节点的数量,M:边的数量
    public static class StronglyConnectedComponents {
        public ArrayList<ArrayList<Integer>> nexts;
        public int n;
        public int[] stack;
        public int stackSize;
        public int[] dfn;
        public int[] low;
        public int cnt;
        public int[] scc;
        public int sccn;

        // 点的编号从1开始，不从0开始
        // 注意：
        // 如果edges里有0,1,2..n这些点,那么容器edges的大小为n+1
        // 但是0点是弃而不用的，所以1..n才是有效的点，所有有效大小是n
        public StronglyConnectedComponents(ArrayList<ArrayList<Integer>> edges) {
            nexts = edges;
            init();
            scc();
        }

        private void init() {
            n = nexts.size();
            stack = new int[n];
            stackSize = 0;
            dfn = new int[n];
            low = new int[n];
            cnt = 0;
            scc = new int[n];
            sccn = 0;
            n--;
        }

        private void scc() {
            for (int i = 1; i <= n; i++) {
                // init()方法里面开始每个点dfn序都是0，表示没有遍历过
                if (dfn[i] == 0) {
                    tarjan(i);
                }
            }
        }

        // 当前来到p点，之前没有遍历过
        private void tarjan(int p) {
            // dfn序从1开始标号
            dfn[p] = low[p] = ++cnt;
            stack[stackSize++] = p;
            // 枚举p的孩子，进行处理
            for (int q : nexts.get(p)) {
                if (dfn[q] == 0) { // 当前后代节点没有被遍历过
                    tarjan(q);
                }
                // q 已经遍历过  1)遍历过，没有结算  2)遍历过，没有结算
                // scc[q] == 0 代表遍历过没有结算
                if (scc[q] == 0) {
                    low[p] = Math.min(low[p], low[q]);
                }
            }
            // low[p] == dfn[p]找到口袋节点
            if (low[p] == dfn[p]) {
                sccn++;
                int top = 0;
                do {
                    top = stack[--stackSize];
                    scc[top] = sccn;
                } while (top != p);
            }
        }

        public int[] getScc() {
            return scc;
        }

        public int getSccn() {
            return sccn;
        }

        public ArrayList<ArrayList<Integer>> getShortGraph() {
            ArrayList<ArrayList<Integer>> shortGraph = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= sccn; i++) {
                shortGraph.add(new ArrayList<Integer>());
            }
            for (int u = 1; u <= n; u++) {
                for (int v : nexts.get(u)) {
                    if (scc[v] != scc[u]) {
                        shortGraph.get(scc[u]).add(scc[v]);
                    }
                }
            }
            return shortGraph;
        }
    }
}
