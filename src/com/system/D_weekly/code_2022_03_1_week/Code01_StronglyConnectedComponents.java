package com.system.D_weekly.code_2022_03_1_week;

import java.util.ArrayList;

/**
 * @author ycb
 * @date 2022/3/5-12:11
 * @description tarjan算法求有向图的强连通分量
 */
public class Code01_StronglyConnectedComponents {

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
            ArrayList<ArrayList<Integer>> shortGraph = new ArrayList<>();
            for (int i = 0; i <= sccn; i++) {
                shortGraph.add(new ArrayList<>());
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
