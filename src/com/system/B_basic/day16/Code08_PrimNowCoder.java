package com.system.B_basic.day16;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ycb
 * @date 2023/4/29-00:17
 * @desc 模拟 ACM 模式的输入输出
 * https://www.nowcoder.com/questionTerminal/c23eab7bb39748b6b224a8a3afbe396b
 */
public class Code08_PrimNowCoder {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
            int n = (int) in.nval;
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int A = (int) in.nval;
                in.nextToken();
                int B = (int) in.nval;
                in.nextToken();
                int cost = (int) in.nval;
                graph.get(A).add(new int[]{B, cost});
                graph.get(B).add(new int[]{A, cost});
            }
            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            boolean[] visited = new boolean[n + 1];
//            for (int[] edge : graph.get(1)) {
//                heap.add(edge);
//            }
            heap.addAll(graph.get(1));
            visited[1] = true;
            int ans = 0;
            while (!heap.isEmpty()) {
                int[] edge = heap.poll();
                int next = edge[0];
                int cost = edge[1];
                if (!visited[next]) {
                    visited[next] = true;
                    ans += cost;
//                    for (int[] e : graph.get(next)) {
//                        heap.add(e);
//                    }
                    heap.addAll(graph.get(next));
                }
            }
            out.println(ans);
            out.flush();
        }
    }
}
